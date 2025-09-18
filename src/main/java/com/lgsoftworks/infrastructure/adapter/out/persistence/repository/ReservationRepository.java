package com.lgsoftworks.infrastructure.adapter.out.persistence.repository;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.reservation.enums.StatusReservation;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.ReservationEntity;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>, JpaSpecificationExecutor<ReservationEntity> {
    List<ReservationEntity> findByFieldIdAndStatus(Long fieldId, Status status);
    List<ReservationEntity> findByField_Venue_IdAndStatus(Long venueId, Status status);
    List<ReservationEntity> findByField_Venue_IdAndStatusAndReservationDate(Long venueId, Status status, LocalDate date);
    List<ReservationEntity> findByFieldIdAndReservationDateAndStatus(Long fieldId, LocalDate date, Status status);
    Optional<ReservationEntity> findByCodeAndStatus(String code, Status status);

    @Modifying
    @Transactional
    @Query("UPDATE ReservationEntity r SET r.status = :status WHERE r.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") Status status);
}
