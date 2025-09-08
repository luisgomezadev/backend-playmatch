package com.lgsoftworks.application.reservation.service;

import com.lgsoftworks.application.reservation.dto.response.TimeSlot;
import com.lgsoftworks.application.reservation.dto.mapper.ReservationModelMapper;
import com.lgsoftworks.domain.common.port.out.CloudinaryImageUploaderPort;
import com.lgsoftworks.domain.exception.FieldByIdNotFoundException;
import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.application.reservation.dto.request.ReservationRequest;
import com.lgsoftworks.application.reservation.dto.response.ReservationAvailabilityDTO;
import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.field.port.out.FieldRepositoryPort;
import com.lgsoftworks.domain.reservation.enums.StatusReservation;
import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.domain.reservation.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.reservation.port.out.ReservationRepositoryPort;
import com.lgsoftworks.domain.reservation.validation.ValidateReservation;
import com.lgsoftworks.domain.user.model.User;
import com.lgsoftworks.domain.user.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationAvailabilityService implements ReservationAvailabilityUseCase {

    private final FieldRepositoryPort fieldRepositoryPort;
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ValidateReservation validateReservation;

    public ReservationAvailabilityService(FieldRepositoryPort fieldRepositoryPort,
                                          UserRepositoryPort userRepositoryPort,
                                          ReservationRepositoryPort reservationRepositoryPort,
                                          CloudinaryImageUploaderPort cloudinaryImageUploaderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.fieldRepositoryPort = fieldRepositoryPort;
        this.validateReservation = new ValidateReservation(reservationRepositoryPort);
        this.reservationRepositoryPort = reservationRepositoryPort;
    }

    @Override
    public List<TimeSlot> getAvailableSlots(Long fieldId, LocalDate date) {
        Field field = fieldRepositoryPort.findById(fieldId)
                .orElseThrow(() -> new FieldByIdNotFoundException(fieldId));

        LocalTime open = field.getOpeningHour();
        LocalTime close = field.getClosingHour();

        List<Reservation> reservations = reservationRepositoryPort.findActiveByFieldIdAndDate(fieldId, date);

        List<TimeSlot> available = new ArrayList<>();
        LocalTime current = open;

        for (Reservation res : reservations) {
            if (current.isBefore(res.getStartTime())) {
                available.add(new TimeSlot(current, res.getStartTime()));
            }
            current = res.getEndTime();
        }

        if (current.isBefore(close)) {
            available.add(new TimeSlot(current, close));
        }

        return available;
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

        validateReservation.validateUserHasReservation(user);
        validateReservation.validateTimeWithinFieldSchedule(reservation, field);
        validateReservation.validateFieldAvailability(reservation, field);

        return Optional.of(ReservationModelMapper.toAvailabilityDTO(reservation));
    }
}
