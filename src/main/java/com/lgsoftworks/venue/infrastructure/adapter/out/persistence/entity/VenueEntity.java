package com.lgsoftworks.venue.infrastructure.adapter.out.persistence.entity;

import com.lgsoftworks.shared.domain.enums.Status;
import com.lgsoftworks.shared.infrastructure.persistence.BaseEntity;
import com.lgsoftworks.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "venue")
@NoArgsConstructor
@Getter
@Setter
public class VenueEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalTime openingHour;

    @Column(nullable = false)
    private LocalTime closingHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false, unique = true)
    private UserEntity admin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}