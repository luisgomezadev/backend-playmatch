package com.lgsoftworks.domain.dto;

import com.lgsoftworks.domain.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.domain.dto.summary.ReservationTeamDTO;
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
    private List<PersonSummaryDTO> members;
    private Long ownerId;
    private List<ReservationTeamDTO> reservations;
}
