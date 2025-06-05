package com.lgsoftworks.infrastructure.adapter.entity;

import com.lgsoftworks.domain.enums.StatusRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "request_player")
@NoArgsConstructor
@Getter
@Setter
public class RequestPlayerEntity extends BaseEntity{
    private String description;
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerEntity player;
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private TeamEntity team;
    private LocalDateTime requestDate;
    @Enumerated(EnumType.STRING)
    private StatusRequest statusRequest;
}
