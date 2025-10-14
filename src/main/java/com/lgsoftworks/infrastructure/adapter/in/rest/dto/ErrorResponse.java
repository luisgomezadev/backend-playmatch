package com.lgsoftworks.infrastructure.adapter.in.rest.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private Integer code;
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;

}
