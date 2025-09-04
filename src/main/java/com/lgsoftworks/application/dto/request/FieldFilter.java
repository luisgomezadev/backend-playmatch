package com.lgsoftworks.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldFilter {

    private String city;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

}
