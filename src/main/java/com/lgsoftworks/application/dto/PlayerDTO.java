package com.lgsoftworks.application.dto;

import com.lgsoftworks.application.dto.summary.TeamSummaryDTO;
import com.lgsoftworks.domain.enums.DocumentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PlayerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private Byte age;
    private String cellphone;
    private DocumentType documentType;
    private String documentNumber;
    private String email;
    private String imageUrl;
    private TeamSummaryDTO team;
}
