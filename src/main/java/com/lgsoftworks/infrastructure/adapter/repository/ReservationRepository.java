package com.lgsoftworks.infrastructure.adapter.repository;

import com.lgsoftworks.infrastructure.adapter.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
}
