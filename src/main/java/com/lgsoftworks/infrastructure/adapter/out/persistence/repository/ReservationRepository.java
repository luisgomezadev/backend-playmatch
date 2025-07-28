package com.lgsoftworks.infrastructure.adapter.out.persistence.repository;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.ReservationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findByFieldId(Long fieldId);
    List<ReservationEntity> findByFieldIdAndStatus(Long fieldId, StatusReservation status);
    List<ReservationEntity> findByUserId(Long userId);
    List<ReservationEntity> findAllByStatus(StatusReservation status);
    Long countByStatusAndUser_Id(StatusReservation status, Long userId);
    Long countByStatusAndField_Id(StatusReservation status, Long fieldId);

    @Query("""
    SELECT r FROM ReservationEntity r
    WHERE (:date IS NULL OR r.reservationDate = :date)
        AND (:status IS NULL OR r.status = :status)
        AND (:userId IS NULL OR r.user.id = :userId)
        AND (:fieldId IS NULL OR r.field.id = :fieldId)
    """)
    Page<ReservationEntity> findByFilters(
            @Param("date") LocalDate date,
            @Param("status") StatusReservation status,
            @Param("userId") Long userId,
            @Param("fieldId") Long fieldId,
            Pageable pageable
    );

    @Modifying
    @Transactional
    @Query("UPDATE ReservationEntity r SET r.status = :status WHERE r.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") StatusReservation status);
}
