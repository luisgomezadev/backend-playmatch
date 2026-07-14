package com.lgsoftworks.field.infrastructure.adapter.out.persistence.entity;

import com.lgsoftworks.field.domain.model.FieldType;
import com.lgsoftworks.shared.infrastructure.persistence.BaseEntity;
import com.lgsoftworks.venue.infrastructure.adapter.out.persistence.entity.VenueEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "field")
@NoArgsConstructor
@Getter
@Setter
public class FieldEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FieldType fieldType;

    @Column(nullable = false)
    private BigDecimal hourlyRate;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    private VenueEntity venue;
}