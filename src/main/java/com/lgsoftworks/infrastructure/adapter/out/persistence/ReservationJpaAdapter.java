package com.lgsoftworks.infrastructure.adapter.out.persistence;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.model.Reservation;
import com.lgsoftworks.domain.port.out.ReservationRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.ReservationEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.ReservationDboMapper;
import com.lgsoftworks.infrastructure.adapter.out.persistence.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Reservation> findAll() {
        return reservationRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(ReservationEntity::getReservationDate).reversed()
                        .thenComparing(ReservationEntity::getStartTime))
                .map(ReservationDboMapper::toModel)
                .toList();
    }

    @Override
    public Page<Reservation> findByFilters(LocalDate date, StatusReservation status, Long userId, Long fieldId, Pageable pageable) {
        Sort sort = Sort.by(Sort.Order.desc("reservationDate"), Sort.Order.asc("startTime"));
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<ReservationEntity> reservationList = reservationRepository.findByFilters(date, status, userId, fieldId, sortedPageable);

        return reservationList.map(ReservationDboMapper::toModel);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findById(id);
        return optionalReservation.map(ReservationDboMapper::toModel);
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity savedReservation = reservationRepository.save(ReservationDboMapper.toDbo(reservation));
        return ReservationDboMapper.toModel(savedReservation);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Reservation> findByFieldId(Long fieldId) {
        return reservationRepository.findByFieldId(fieldId)
                .stream()
                .sorted(Comparator.comparing(ReservationEntity::getReservationDate).reversed()
                        .thenComparing(ReservationEntity::getStartTime))
                .map(ReservationDboMapper::toModel)
                .toList();
    }

    @Override
    public List<Reservation> findByFieldIdAndStatus(Long fieldId, StatusReservation status) {
        return reservationRepository.findByFieldIdAndStatus(fieldId, status)
                .stream()
                .map(ReservationDboMapper::toModel)
                .toList();
    }

    @Override
    public List<Reservation> findByUserId(Long userId) {
        return reservationRepository.findByUserId(userId)
                .stream()
                .sorted(Comparator.comparing(ReservationEntity::getReservationDate).reversed()
                        .thenComparing(ReservationEntity::getStartTime))
                .map(ReservationDboMapper::toModel)
                .toList();
    }

    @Override
    public List<Reservation> findAllByStatus(StatusReservation status) {
        return reservationRepository.findAllByStatus(status)
                .stream()
                .sorted(Comparator.comparing(ReservationEntity::getReservationDate).reversed()
                        .thenComparing(ReservationEntity::getStartTime))
                .map(ReservationDboMapper::toModel)
                .toList();
    }

    @Override
    public void updateStatus(Long reservationId, StatusReservation status) {
        reservationRepository.updateStatusById(reservationId, status);
    }

    @Override
    public Long countReservationsByUserAndStatus(StatusReservation statusReservation, Long userId) {
        return reservationRepository.countByStatusAndUser_Id(statusReservation, userId);
    }

    @Override
    public Long countReservationsByFieldAndStatus(StatusReservation statusReservation, Long fieldId) {
        return reservationRepository.countByStatusAndField_Id(statusReservation, fieldId);
    }
}
