package com.lgsoftworks.venue.application.service;

import com.lgsoftworks.auth.application.service.CurrentUserService;
import com.lgsoftworks.field.application.dto.mapper.FieldModelMapper;
import com.lgsoftworks.field.application.dto.response.FieldDTO;
import com.lgsoftworks.field.domain.model.Field;
import com.lgsoftworks.field.domain.port.out.FieldRepositoryPort;
import com.lgsoftworks.field.domain.service.FieldUniquenessValidator;
import com.lgsoftworks.user.domain.model.User;
import com.lgsoftworks.venue.application.dto.mapper.VenueModelMapper;
import com.lgsoftworks.venue.application.dto.request.CreateFieldItemRequest;
import com.lgsoftworks.venue.application.dto.request.CreateVenueWithFieldsRequest;
import com.lgsoftworks.venue.application.dto.request.VenueRequest;
import com.lgsoftworks.venue.application.dto.response.VenueWithFieldsDTO;
import com.lgsoftworks.venue.application.port.in.CreateVenueWithFieldsUseCase;
import com.lgsoftworks.venue.domain.exception.InvalidVenueDataException;
import com.lgsoftworks.venue.domain.model.Venue;
import com.lgsoftworks.venue.domain.model.VenueId;
import com.lgsoftworks.venue.domain.port.out.VenueRepositoryPort;
import com.lgsoftworks.venue.domain.service.VenueUniquenessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateVenueWithFieldsService implements CreateVenueWithFieldsUseCase {

    private final VenueRepositoryPort venueRepositoryPort;
    private final FieldRepositoryPort fieldRepositoryPort;
    private final VenueUniquenessValidator venueUniquenessValidator;
    private final FieldUniquenessValidator fieldUniquenessValidator;
    private final FieldModelMapper fieldModelMapper;
    private final VenueModelMapper venueModelMapper;
    private final CurrentUserService currentUserService;

    @Override
    @Transactional
    public VenueWithFieldsDTO execute(CreateVenueWithFieldsRequest request) {
        Venue venue = createVenue(request.getVenue());
        List<FieldDTO> fields = createFields(venue.getId(), request.getFields());

        return new VenueWithFieldsDTO(venueModelMapper.toDTO(venue), fields);
    }

    private Venue createVenue(VenueRequest venueRequest) {
        User curreUser = currentUserService.getCurrentUser();

        venueUniquenessValidator.validate(venueRequest.getCode());

        Venue venue = Venue.create(
                venueRequest.getCode(),
                venueRequest.getName(),
                venueRequest.getCity(),
                venueRequest.getAddress(),
                venueRequest.getOpeningHour(),
                venueRequest.getClosingHour(),
                curreUser.getId()
        );
        return venueRepositoryPort.save(venue);
    }

    private List<FieldDTO> createFields(Long venueId, List<CreateFieldItemRequest> fieldRequests) {
        validateNoDuplicateNamesInRequest(fieldRequests);

        VenueId venueIdVO = new VenueId(venueId);

        return fieldRequests.stream()
                .map(item -> {
                    fieldUniquenessValidator.validate(venueId, item.getName());
                    Field field = Field.create(
                            item.getName(),
                            item.getFieldType(),
                            item.getHourlyRate(),
                            venueIdVO
                    );
                    Field saved = fieldRepositoryPort.save(field);
                    return fieldModelMapper.toDTO(saved);
                })
                .toList();
    }

    private void validateNoDuplicateNamesInRequest(List<CreateFieldItemRequest> fieldRequests) {
        long distinctNames = fieldRequests.stream()
                .map(f -> f.getName().toLowerCase())
                .distinct()
                .count();
        if (distinctNames != fieldRequests.size()) {
            throw new InvalidVenueDataException("No puede enviar dos canchas con el mismo nombre en la misma petición");
        }
    }
}