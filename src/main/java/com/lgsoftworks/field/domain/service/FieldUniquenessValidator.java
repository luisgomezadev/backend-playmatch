package com.lgsoftworks.field.domain.service;

import com.lgsoftworks.field.domain.exception.DuplicateFieldNameException;
import com.lgsoftworks.field.domain.port.out.FieldRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FieldUniquenessValidator {

    private final FieldRepositoryPort fieldRepositoryPort;

    public void validate(Long venueId, String fieldName) {
        boolean exists = fieldRepositoryPort.findByVenueId(venueId).stream()
                .anyMatch(field -> field.getName().equalsIgnoreCase(fieldName));
        if (exists) {
            throw new DuplicateFieldNameException(fieldName, venueId);
        }
    }

    public void validateForUpdate(Long venueId, String fieldName, Long excludeFieldId) {
        boolean exists = fieldRepositoryPort.findByVenueId(venueId).stream()
                .filter(field -> !field.getId().equals(excludeFieldId))
                .anyMatch(field -> field.getName().equalsIgnoreCase(fieldName));
        if (exists) {
            throw new DuplicateFieldNameException(fieldName, venueId);
        }
    }
}