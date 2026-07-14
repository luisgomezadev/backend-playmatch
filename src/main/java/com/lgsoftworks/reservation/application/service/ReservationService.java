package com.lgsoftworks.reservation.application.service;

import com.lgsoftworks.reservation.application.dto.mapper.ReservationModelMapper;
import com.lgsoftworks.reservation.application.dto.response.ReservationDTO;
import com.lgsoftworks.reservation.application.port.in.ReservationUseCase;
import com.lgsoftworks.reservation.domain.exception.ReservationByIdNotFoundException;
import com.lgsoftworks.reservation.domain.model.Reservation;
import com.lgsoftworks.reservation.domain.port.out.ReservationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationUseCase {

    private final ReservationRepositoryPort reservationRepositoryPort;

    @Override
    public Optional<ReservationDTO> findById(Long id) {
        return reservationRepositoryPort.findById(id).map(ReservationModelMapper::toDTO);
    }

    @Override
    public Optional<ReservationDTO> findByCode(String code) {
        return reservationRepositoryPort.findByCode(code).map(ReservationModelMapper::toDTO);
    }

    @Override
    public List<ReservationDTO> findByFieldId(Long fieldId) {
        return reservationRepositoryPort.findActiveByFieldId(fieldId).stream()
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReservationDTO> findByVenueId(Long venueId) {
        return reservationRepositoryPort.findActiveByVenueId(venueId).stream()
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReservationDTO> findByVenueIdAndDate(Long venueId, LocalDate date) {
        return reservationRepositoryPort.findActiveByVenueIdAndDate(venueId, date).stream()
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    @Override
    public ReservationDTO cancel(Long id) {
        Reservation reservation = reservationRepositoryPort.findById(id)
                .orElseThrow(() -> new ReservationByIdNotFoundException(id));
        reservation.cancel();
        Reservation saved = reservationRepositoryPort.save(reservation);
        return ReservationModelMapper.toDTO(saved);
    }
}