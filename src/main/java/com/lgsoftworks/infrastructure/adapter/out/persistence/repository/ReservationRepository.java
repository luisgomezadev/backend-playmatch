package com.lgsoftworks.infrastructure.adapter.out.persistence.repository;

import com.lgsoftworks.domain.reservation.enums.StatusReservation;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>, JpaSpecificationExecutor<ReservationEntity> {
    List<ReservationEntity> findByFieldId(Long fieldId);
    List<ReservationEntity> findByFieldIdAndStatus(Long fieldId, StatusReservation status);
    List<ReservationEntity> findByUserId(Long userId);
    List<ReservationEntity> findAllByStatus(StatusReservation status);
    List<ReservationEntity> findTop3ByFieldIdOrderByCreatedDateDesc(Long fieldId);
    List<ReservationEntity> findByFieldIdAndReservationDateAndStatus(Long fieldId, LocalDate date, StatusReservation status);
    Long countByStatusAndUser_Id(StatusReservation status, Long userId);
    Long countByStatusAndField_Id(StatusReservation status, Long fieldId);

    @Modifying
    @Transactional
    @Query("UPDATE ReservationEntity r SET r.status = :status WHERE r.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") StatusReservation status);
}
