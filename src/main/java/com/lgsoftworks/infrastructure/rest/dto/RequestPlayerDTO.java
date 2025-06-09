package com.lgsoftworks.infrastructure.rest.dto;

import com.lgsoftworks.infrastructure.rest.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.infrastructure.rest.dto.summary.TeamSummaryDTO;
import com.lgsoftworks.domain.enums.StatusRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class RequestPlayerDTO {
    private Long id;
    private String description;
    private PersonSummaryDTO player;
    private TeamSummaryDTO team;
    private LocalDateTime requestDate;
    private StatusRequest statusRequest;
}
