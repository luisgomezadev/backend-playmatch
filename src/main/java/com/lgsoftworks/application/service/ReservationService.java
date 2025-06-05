package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.ReservationModelMapper;
import com.lgsoftworks.domain.exception.ReservationByIdNotFoundException;
import com.lgsoftworks.domain.port.in.ReservationUseCase;
import com.lgsoftworks.domain.dto.ReservationDTO;
import com.lgsoftworks.domain.dto.request.ReservationRequest;
import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.exception.FieldByIdNotFoundException;
import com.lgsoftworks.domain.exception.TeamByIdNotFoundException;
import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.domain.model.Reservation;
import com.lgsoftworks.domain.model.Team;
import com.lgsoftworks.domain.port.out.FieldRepositoryPort;
import com.lgsoftworks.domain.port.out.ReservationRepositoryPort;
import com.lgsoftworks.domain.port.out.TeamRepositoryPort;
import com.lgsoftworks.domain.validator.ReservationValidator;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ReservationService implements ReservationUseCase {

    private final ReservationRepositoryPort reservationRepositoryPort;
    private final FieldRepositoryPort fieldRepositoryPort;
    private final TeamRepositoryPort teamRepositoryPort;

    @Override
    public List<ReservationDTO> findAll() {
        List<Reservation> reservationList = reservationRepositoryPort.findAll();
        return reservationList.stream()
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<ReservationDTO> findById(Long id) {
        Optional<Reservation> reservation = reservationRepositoryPort.findById(id);
        return reservation.map(ReservationModelMapper::toDTO);
    }

    @Override
    public ReservationDTO save(ReservationRequest reservationRequest) {

        Field field = fieldRepositoryPort.findById(reservationRequest.getField().getId())
                .orElseThrow(() -> new FieldByIdNotFoundException(reservationRequest.getField().getId()));

        Team team = teamRepositoryPort.findById(reservationRequest.getTeam().getId())
                .orElseThrow(() -> new TeamByIdNotFoundException(reservationRequest.getTeam().getId()));

        LocalTime time = reservationRequest.getStartTime().plusHours(reservationRequest.getHours());

        reservationRequest.setStatus(StatusReservation.ACTIVE); // Estado por defecto cuando se crea una reserva

        Reservation reservation = ReservationModelMapper.toModelRequest(reservationRequest);
        reservation.setEndTime(time);
        reservation.setField(field);
        reservation.setTeam(team);

        ReservationValidator.validateTeamHasReservation(team);
        ReservationValidator.validateTimeWithinFieldSchedule(reservation, field);
        ReservationValidator.validateFieldAvailability(reservation, field);

        Reservation savedReservation = reservationRepositoryPort.save(reservation);
        return ReservationModelMapper.toDTO(savedReservation);
    }

    @Override
    public ReservationDTO update(ReservationRequest reservationRequest) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        reservationRepositoryPort.deleteById(id);
    }

    @Override
    public void finalizeReservation(Long id) {
        Reservation reservation = reservationRepositoryPort.findById(id)
                .orElseThrow(() -> new ReservationByIdNotFoundException(id));

        reservation.setStatus(StatusReservation.FINISHED);

        reservationRepositoryPort.save(reservation);
    }
}
