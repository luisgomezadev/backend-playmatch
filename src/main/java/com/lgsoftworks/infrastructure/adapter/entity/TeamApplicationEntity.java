package com.lgsoftworks.infrastructure.adapter.entity;

import com.lgsoftworks.domain.enums.StatusRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "team_application")
@NoArgsConstructor
@Getter
@Setter
public class TeamApplicationEntity extends BaseEntity{
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerEntity player;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private TeamEntity team;
    private LocalDateTime applicationDate;
    @Enumerated(EnumType.STRING)
    private StatusRequest statusRequest;
}
