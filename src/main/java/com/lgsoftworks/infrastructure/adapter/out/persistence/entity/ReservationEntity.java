package com.lgsoftworks.infrastructure.adapter.out.persistence.entity;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.reservation.enums.ReservationDuration;
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
public class ReservationEntity extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String user;

    @Column(nullable = false, length = 10)
    private String cellphone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", nullable = false)
    private FieldEntity field;

    // Enum con duración
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
    private Status status;
}
