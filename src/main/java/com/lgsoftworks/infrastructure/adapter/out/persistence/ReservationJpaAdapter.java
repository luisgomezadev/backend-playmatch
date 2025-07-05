package com.lgsoftworks.infrastructure.adapter.out.persistence;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.model.Reservation;
import com.lgsoftworks.domain.port.out.ReservationRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.ReservationEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.ReservationDboMapper;
import com.lgsoftworks.infrastructure.adapter.out.persistence.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public List<Reservation> findByTeamId(Long teamId) {
        return reservationRepository.findByTeamId(teamId)
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
    public Long countReservationsByTeamAndStatus(StatusReservation statusReservation, Long teamId) {
        return reservationRepository.countByStatusAndTeam_Id(statusReservation, teamId);
    }

    @Override
    public Long countReservationsByFieldAndStatus(StatusReservation statusReservation, Long fieldId) {
        return reservationRepository.countByStatusAndField_Id(statusReservation, fieldId);
    }
}
