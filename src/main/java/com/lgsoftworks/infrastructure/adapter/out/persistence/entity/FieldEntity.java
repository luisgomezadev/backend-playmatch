package com.lgsoftworks.infrastructure.adapter.out.persistence.entity;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.field.enums.FieldType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "field")
@NoArgsConstructor
@Getter
@Setter
public class FieldEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FieldType fieldType;

    @Column(nullable = false)
    private BigDecimal hourlyRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    private VenueEntity venue;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationEntity> reservations = new ArrayList<>();
}
