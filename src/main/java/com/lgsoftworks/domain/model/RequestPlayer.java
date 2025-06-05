package com.lgsoftworks.domain.model;

import com.lgsoftworks.domain.enums.StatusRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestPlayer {
    private Long id;
    private String description;
    private Player player;
    private Team team;
    private LocalDateTime requestDate;
    private StatusRequest statusRequest;
}
