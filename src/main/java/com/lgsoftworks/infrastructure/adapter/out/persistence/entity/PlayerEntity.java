package com.lgsoftworks.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
public class PlayerEntity extends UserEntity {

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;

}
