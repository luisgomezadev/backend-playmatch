package com.lgsoftworks.application.dto;

import com.lgsoftworks.application.dto.summary.ReservationTeamDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TeamDTO {
    private Long id;
    private String name;
    private String neighborhood;
    private String city;
    private Integer maxPlayers;
    private List<UserDTO> members;
    private Long ownerId;
    private String imageUrl;
    private List<ReservationTeamDTO> reservations;
}
