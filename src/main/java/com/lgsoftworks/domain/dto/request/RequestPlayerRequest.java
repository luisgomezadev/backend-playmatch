package com.lgsoftworks.domain.dto.request;

import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.model.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestPlayerRequest {
    private Long id;
    private String description;
    private Player player;
    private Team team;
}
