package com.lgsoftworks.reservation.infrastructure.adapter.out.persistence.entity;

import com.lgsoftworks.field.infrastructure.adapter.out.persistence.entity.FieldEntity;
import com.lgsoftworks.reservation.domain.model.ReservationDuration;
import com.lgsoftworks.reservation.domain.model.ReservationStatus;
import com.lgsoftworks.shared.infrastructure.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservation")
@NoArgsConstructor
@Getter
@Setter
public class ReservationEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String code;

    @Column(name = "customerName", nullable = false)
    private String customerName;

    @Column(nullable = false, length = 10)
    private String cellphone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", nullable = false)
    private FieldEntity field;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationDuration duration;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;
}
