package com.lgsoftworks.application.reservation.service;

import com.lgsoftworks.application.reservation.dto.mapper.ReservationModelMapper;
import com.lgsoftworks.application.reservation.dto.response.ReservationDTO;
import com.lgsoftworks.application.reservation.dto.request.ReservationRequest;
import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.domain.reservation.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.reservation.port.in.ReservationUseCase;
import com.lgsoftworks.domain.reservation.port.out.ReservationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationUseCase {

    private final ReservationRepositoryPort reservationRepositoryPort;
    private final ReservationAvailabilityUseCase reservationAvailabilityUseCase;

    @Override
    public Optional<ReservationDTO> findById(Long id) {
        Optional<Reservation> reservation = reservationRepositoryPort.findById(id);
        return reservation.map(ReservationModelMapper::toDTO);
    }

    @Override
    public Optional<ReservationDTO> findByCode(String code) {
        Optional<Reservation> reservation = reservationRepositoryPort.findByCode(code);
        return reservation.map(ReservationModelMapper::toDTO);
    }

    @Override
    public ReservationDTO save(ReservationRequest reservationRequest) {

        Optional<ReservationDTO> reservationDTO = reservationAvailabilityUseCase.reservationAvailability(reservationRequest);

        if (reservationDTO.isPresent()) {
            Reservation reservation = ReservationModelMapper.toModel(reservationDTO.get());

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
    public void updateStatus(Long reservationId, Status status) {
        reservationRepositoryPort.updateStatus(reservationId, status);
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
    public List<ReservationDTO> findByVenueId(Long venueId) {
        List<Reservation> reservationList = reservationRepositoryPort.findByVenueId(venueId);
        return reservationList.stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate))
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReservationDTO> findByVenueIdAndDate(Long venueId, LocalDate date) {
        List<Reservation> reservationList = reservationRepositoryPort.findByVenueIdAndDate(venueId, date);
        return reservationList
                .stream()
                .sorted(Comparator.comparing(Reservation::getStartTime))
                .map(ReservationModelMapper::toDTO)
                .toList();
    }
}
