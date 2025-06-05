package com.lgsoftworks.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TeamRequest {
    private Long id;
    private String name;
    private String neighborhood;
    private String city;
    private Integer maxPlayers;
    private Long ownerId;
}
