package com.lgsoftworks.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "field_admin")
@NoArgsConstructor
@Getter
@Setter
public class FieldAdminEntity extends UserEntity {

    @OneToOne
    @JoinColumn(name = "field_id")
    private FieldEntity field;

}
