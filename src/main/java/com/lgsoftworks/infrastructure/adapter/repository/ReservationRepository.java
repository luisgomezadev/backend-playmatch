package com.lgsoftworks.infrastructure.adapter.repository;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.infrastructure.adapter.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findByFieldId(Long fieldId);
    List<ReservationEntity> findByTeamId(Long teamId);
    List<ReservationEntity> findAllByStatus(StatusReservation status);
    Long countByStatusAndTeam_Id(StatusReservation status, Long teamId);
    Long countByStatusAndField_Id(StatusReservation status, Long fieldId);
}
