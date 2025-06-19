package com.lgsoftworks.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends User {
    private Team team;
}
