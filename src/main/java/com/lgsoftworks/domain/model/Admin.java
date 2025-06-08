package com.lgsoftworks.domain.model;

import com.lgsoftworks.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin extends Person{
    private Field field;
}
