package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.ReservationModelMapper;
import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.exception.FieldByIdNotFoundException;
import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.domain.model.Reservation;
import com.lgsoftworks.domain.model.User;
import com.lgsoftworks.domain.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.port.out.CloudinaryImageUploaderPort;
import com.lgsoftworks.domain.port.out.FieldRepositoryPort;
import com.lgsoftworks.domain.port.out.ReservationRepositoryPort;
import com.lgsoftworks.domain.port.out.UserRepositoryPort;
import com.lgsoftworks.domain.validation.ReservationValidator;
import com.lgsoftworks.application.dto.request.ReservationRequest;
import com.lgsoftworks.application.dto.ReservationAvailabilityDTO;
import com.lgsoftworks.domain.validation.ValidateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class ReservationAvailabilityService implements ReservationAvailabilityUseCase {

    private final FieldRepositoryPort fieldRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ReservationValidator reservationValidator;

    public ReservationAvailabilityService(FieldRepositoryPort fieldRepositoryPort,
                                          UserRepositoryPort userRepositoryPort,
                                          ReservationRepositoryPort reservationRepositoryPort,
                                          CloudinaryImageUploaderPort cloudinaryImageUploaderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.fieldRepositoryPort = fieldRepositoryPort;
        this.reservationValidator = new ReservationValidator(reservationRepositoryPort);
    }

    @Override
    public Optional<ReservationAvailabilityDTO> reservationAvailability(ReservationRequest reservationRequest) {
        Field field = fieldRepositoryPort.findById(reservationRequest.getFieldId())
                .orElseThrow(() -> new FieldByIdNotFoundException(reservationRequest.getFieldId()));

        User user = userRepositoryPort.findById(reservationRequest.getUserId())
                .orElseThrow(() -> new UserByIdNotFoundException(reservationRequest.getUserId()));

        LocalTime time = reservationRequest.getStartTime().plusHours(reservationRequest.getHours());

        reservationRequest.setStatus(StatusReservation.ACTIVE); // Estado por defecto cuando se crea una reserva

        Reservation reservation = ReservationModelMapper.toModelRequest(reservationRequest);
        reservation.setEndTime(time);
        reservation.setField(field);
        reservation.setUser(user);

        reservationValidator.validateUserHasReservation(user);
        reservationValidator.validateTimeWithinFieldSchedule(reservation, field);
        reservationValidator.validateFieldAvailability(reservation, field);

        return Optional.of(ReservationModelMapper.toAvailabilityDTO(reservation));
    }
}
