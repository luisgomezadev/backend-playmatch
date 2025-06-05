package com.lgsoftworks.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Team {
    private Long id;
    private String name;
    private String neighborhood;
    private String city;
    private Integer maxPlayers;
    private Long ownerId;
    private List<Player> members = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
}
