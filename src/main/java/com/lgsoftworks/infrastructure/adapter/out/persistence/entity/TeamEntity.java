package com.lgsoftworks.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
@NoArgsConstructor
@Getter
@Setter
public class TeamEntity extends BaseEntity{
    private String name;
    private String neighborhood;
    private String city;
    private Integer maxPlayers;

    @Column(unique = true)
    private Long ownerId;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<PlayerEntity> members = new ArrayList<>();

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReservationEntity> reservations = new ArrayList<>();
}
