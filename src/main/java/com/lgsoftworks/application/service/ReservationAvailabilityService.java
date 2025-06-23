package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.ReservationModelMapper;
import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.exception.FieldByIdNotFoundException;
import com.lgsoftworks.domain.exception.TeamByIdNotFoundException;
import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.domain.model.Reservation;
import com.lgsoftworks.domain.model.Team;
import com.lgsoftworks.domain.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.port.out.FieldRepositoryPort;
import com.lgsoftworks.domain.port.out.TeamRepositoryPort;
import com.lgsoftworks.domain.validation.ReservationValidator;
import com.lgsoftworks.infrastructure.rest.dto.request.ReservationRequest;
import com.lgsoftworks.infrastructure.rest.dto.summary.ReservationAvailabilityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationAvailabilityService implements ReservationAvailabilityUseCase {

    private final FieldRepositoryPort fieldRepositoryPort;
    private final TeamRepositoryPort teamRepositoryPort;

    @Override
    public Optional<ReservationAvailabilityDTO> reservationAvailability(ReservationRequest reservationRequest) {
        Field field = fieldRepositoryPort.findById(reservationRequest.getFieldId())
                .orElseThrow(() -> new FieldByIdNotFoundException(reservationRequest.getFieldId()));

        Team team = teamRepositoryPort.findById(reservationRequest.getTeamId())
                .orElseThrow(() -> new TeamByIdNotFoundException(reservationRequest.getTeamId()));

        LocalTime time = reservationRequest.getStartTime().plusHours(reservationRequest.getHours());

        reservationRequest.setStatus(StatusReservation.ACTIVE); // Estado por defecto cuando se crea una reserva

        Reservation reservation = ReservationModelMapper.toModelRequest(reservationRequest);
        reservation.setEndTime(time);
        reservation.setField(field);
        reservation.setTeam(team);

        ReservationValidator.validateTeamHasReservation(team);
        ReservationValidator.validateTimeWithinFieldSchedule(reservation, field);
        ReservationValidator.validateFieldAvailability(reservation, field);

        return Optional.of(ReservationModelMapper.toAvailabilityDTO(reservation));
    }
}
