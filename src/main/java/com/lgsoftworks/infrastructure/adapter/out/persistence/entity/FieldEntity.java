package com.lgsoftworks.infrastructure.adapter.out.persistence.entity;

import com.lgsoftworks.domain.enums.Status;
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
    private String name;
    private String city;
    private String address;
    private BigDecimal hourlyRate;
    private LocalTime openingHour;
    private LocalTime closingHour;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "admin_id", unique = true)
    private UserEntity admin;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "field", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReservationEntity> reservations = new ArrayList<>();
}
