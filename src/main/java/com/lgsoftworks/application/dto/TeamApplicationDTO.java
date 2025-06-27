package com.lgsoftworks.application.dto;

import com.lgsoftworks.application.dto.summary.PlayerSummaryDTO;
import com.lgsoftworks.application.dto.summary.TeamSummaryDTO;
import com.lgsoftworks.domain.enums.StatusRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TeamApplicationDTO {
    private Long id;
    private String description;
    private PlayerSummaryDTO player;
    private TeamSummaryDTO team;
    private LocalDateTime applicationDate;
    private StatusRequest statusRequest;
}
