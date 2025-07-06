package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.ReservationModelMapper;
import com.lgsoftworks.domain.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.port.in.ReservationUseCase;
import com.lgsoftworks.application.dto.ReservationDTO;
import com.lgsoftworks.application.dto.request.ReservationRequest;
import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.model.Reservation;
import com.lgsoftworks.domain.port.out.FieldRepositoryPort;
import com.lgsoftworks.domain.port.out.ReservationRepositoryPort;
import com.lgsoftworks.domain.port.out.TeamRepositoryPort;
import com.lgsoftworks.application.dto.summary.ReservationAvailabilityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationUseCase {

    private final ReservationRepositoryPort reservationRepositoryPort;
    private final FieldRepositoryPort fieldRepositoryPort;
    private final TeamRepositoryPort teamRepositoryPort;
    private final ReservationAvailabilityUseCase reservationAvailabilityUseCase;

    @Override
    public List<ReservationDTO> findAll() {
        List<Reservation> reservationList = reservationRepositoryPort.findAll();
        return reservationList.stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate).reversed()
                        .thenComparing(Reservation::getStartTime))
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReservationDTO> findByFilters(LocalDate date, StatusReservation status, Long teamId, Long fieldId) {
        List<Reservation> reservationList = reservationRepositoryPort.findByFilters(date, status, teamId, fieldId);
        return reservationList.stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate).reversed()
                        .thenComparing(Reservation::getStartTime))
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

        Optional<ReservationAvailabilityDTO> reservationDTO = reservationAvailabilityUseCase.reservationAvailability(reservationRequest);

        if (reservationDTO.isPresent()) {
            Reservation reservation = ReservationModelMapper.toModelOfAvailability(reservationDTO.get());

            Reservation savedReservation = reservationRepositoryPort.save(reservation);
            return ReservationModelMapper.toDTO(savedReservation);
        }
        return null;
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
    public void updateStatus(Long id, StatusReservation status) {
        reservationRepositoryPort.updateStatus(id, status);
    }

    @Override
    public List<ReservationDTO> findByFieldId(Long fieldId) {
        List<Reservation> reservationList = reservationRepositoryPort.findByFieldId(fieldId);
        return reservationList.stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate).reversed()
                        .thenComparing(Reservation::getStartTime))
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReservationDTO> findByTeamId(Long teamId) {
        List<Reservation> reservationList = reservationRepositoryPort.findByTeamId(teamId);
        return reservationList.stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate).reversed()
                        .thenComparing(Reservation::getStartTime))
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReservationDTO> findAllByStatus(StatusReservation status) {
        List<Reservation> reservationList = reservationRepositoryPort.findAllByStatus(status);
        return reservationList.stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate).reversed()
                        .thenComparing(Reservation::getStartTime))
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    public void autoFinalizeExpiredReservations() {
        List<Reservation> activeReservations = reservationRepositoryPort.findAllByStatus(StatusReservation.ACTIVE);
        LocalDateTime now = LocalDateTime.now();

        for (Reservation reservation : activeReservations) {
            StatusReservation originalStatus = reservation.getStatus();
            reservation.finalizeIfExpired(now);

            if (reservation.getStatus() != originalStatus) {
                reservationRepositoryPort.save(reservation);
            }
        }
    }
}
