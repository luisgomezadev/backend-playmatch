package com.lgsoftworks.infrastructure.adapter.out.persistence;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.domain.reservation.port.out.ReservationRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.ReservationEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.ReservationDboMapper;
import com.lgsoftworks.infrastructure.adapter.out.persistence.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReservationJpaAdapter implements ReservationRepositoryPort {

    private final ReservationRepository reservationRepository;

    @Override
    public Optional<Reservation> findById(Long id) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findById(id);
        return optionalReservation.map(ReservationDboMapper::toModel);
    }

    @Override
    public Optional<Reservation> findByCode(String code) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findByCodeAndStatus(code, Status.ACTIVE);
        return optionalReservation.map(ReservationDboMapper::toModel);
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity savedReservation = reservationRepository.save(ReservationDboMapper.toDbo(reservation));
        return ReservationDboMapper.toModel(savedReservation);
    }

    @Override
    public void updateStatus(Long reservationId, Status status) {
        reservationRepository.updateStatusById(reservationId, status);
    }

    @Override
    public List<Reservation> findByFieldId(Long fieldId) {
        return reservationRepository.findByFieldIdAndStatus(fieldId, Status.ACTIVE)
                .stream()
                .sorted(Comparator.comparing(ReservationEntity::getReservationDate))
                .map(ReservationDboMapper::toModel)
                .toList();
    }

    @Override
    public List<Reservation> findByVenueId(Long venueId) {
        return reservationRepository.findByField_Venue_IdAndStatus(venueId, Status.ACTIVE)
                .stream()
                .sorted(Comparator.comparing(ReservationEntity::getReservationDate))
                .map(ReservationDboMapper::toModel)
                .toList();
    }

    @Override
    public List<Reservation> findByFieldIdAndDate(Long fieldId, LocalDate date) {
        return reservationRepository.findByFieldIdAndReservationDateAndStatus(fieldId, date, Status.ACTIVE)
                .stream()
                .sorted(Comparator.comparing(ReservationEntity::getStartTime))
                .map(ReservationDboMapper::toModel)
                .toList();
    }

    @Override
    public List<Reservation> findByVenueIdAndDate(Long venueId, LocalDate date) {
        return reservationRepository.findByField_Venue_IdAndStatusAndReservationDate(venueId, Status.ACTIVE, date)
                .stream()
                .sorted(Comparator.comparing(ReservationEntity::getStartTime))
                .map(ReservationDboMapper::toModel)
                .toList();
    }
}
