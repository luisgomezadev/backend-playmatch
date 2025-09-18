package com.lgsoftworks.application.field.dto.response;

import com.lgsoftworks.domain.field.enums.FieldType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class FieldDTO {
    private Long id;
    private String name;
    private FieldType fieldType;
    private BigDecimal hourlyRate;
}
