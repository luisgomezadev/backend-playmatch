package com.lgsoftworks.application.venue.service;

import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.field.dto.mapper.FieldModelMapper;
import com.lgsoftworks.application.field.dto.request.FieldRequest;
import com.lgsoftworks.application.venue.dto.mapper.VenueModelMapper;
import com.lgsoftworks.application.venue.dto.request.VenueFilter;
import com.lgsoftworks.application.venue.dto.request.VenueRequest;
import com.lgsoftworks.application.venue.dto.response.VenueDTO;
import com.lgsoftworks.domain.common.util.GenerateCodeUtil;
import com.lgsoftworks.domain.exception.UserAlreadyAssignedAsAdminException;
import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.exception.VenueByIdNotFoundException;
import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.user.model.User;
import com.lgsoftworks.domain.user.port.out.UserRepositoryPort;
import com.lgsoftworks.domain.venue.model.Venue;
import com.lgsoftworks.domain.venue.port.in.VenueUseCase;
import com.lgsoftworks.domain.venue.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VenueService implements VenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Optional<VenueDTO> findById(Long id) {
        Optional<Venue> optionalVenue = venueRepositoryPort.findById(id);
        return optionalVenue.map(VenueModelMapper::toDTO);
    }

    @Override
    public Optional<VenueDTO> findByAdminId(Long adminId) {
        Optional<Venue> optionalVenue = venueRepositoryPort.findByAdminId(adminId);
        return optionalVenue.map(VenueModelMapper::toDTO);
    }

    @Override
    public Optional<VenueDTO> findByCode(String code) {
        Optional<Venue> optionalVenue = venueRepositoryPort.findByCode(code);
        return optionalVenue.map(VenueModelMapper::toDTO);
    }

    @Override
    public PageResponse<VenueDTO> searchVenues(VenueFilter filter, Pageable pageable) {
        Page<VenueDTO> venueDTOS = venueRepositoryPort.searchVenues(filter, pageable)
                .map(VenueModelMapper::toDTO);
        return new PageResponse<>(
                venueDTOS.getContent(),
                venueDTOS.getNumber(),
                venueDTOS.getSize(),
                venueDTOS.getTotalElements(),
                venueDTOS.getTotalPages(),
                venueDTOS.isLast()
        );
    }

    @Override
    public VenueDTO save(VenueRequest venueRequest) {
        User user = userRepositoryPort.findById(venueRequest.getAdminId())
                .orElseThrow(() -> new UserByIdNotFoundException(venueRequest.getAdminId()));

        if (existsByAdminId(user.getId())) {
            throw new UserAlreadyAssignedAsAdminException(user.getId());
        }

        Venue venue = VenueModelMapper.toModelRequest(venueRequest);
        venue.setAdmin(user);

        List<Field> fields = venueRequest.getFields().stream()
                .map(FieldModelMapper::toModelRequest)
                .peek(f -> f.setVenue(venue))
                .toList();

        venue.setFields(fields);

        Venue savedVenue = venueRepositoryPort.save(venue);
        return VenueModelMapper.toDTO(savedVenue);
    }

    @Override
    @Transactional
    public VenueDTO update(VenueRequest venueRequest) {
        // 1️⃣ Obtener venue existente
        Venue existingVenue = venueRepositoryPort.findById(venueRequest.getId())
                .orElseThrow(() -> new RuntimeException("Venue con ID " + venueRequest.getId() + " no encontrado"));

        // 2️⃣ Obtener admin
        User user = userRepositoryPort.findById(venueRequest.getAdminId())
                .orElseThrow(() -> new UserByIdNotFoundException(venueRequest.getAdminId()));

        // 3️⃣ Actualizar datos básicos
        existingVenue.setName(venueRequest.getName());
        existingVenue.setCode(venueRequest.getCode());
        existingVenue.setCity(venueRequest.getCity());
        existingVenue.setAddress(venueRequest.getAddress());
        existingVenue.setOpeningHour(venueRequest.getOpeningHour());
        existingVenue.setClosingHour(venueRequest.getClosingHour());
        existingVenue.setStatus(venueRequest.getStatus());
        existingVenue.setAdmin(user);

        // 4️⃣ Manejar canchas (fields)
        // IDs que vienen del request
        Set<Long> incomingFieldIds = venueRequest.getFields().stream()
                .map(FieldRequest::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // Eliminar canchas que ya no están en la lista
        existingVenue.getFields().removeIf(field -> !incomingFieldIds.contains(field.getId()));

        // 5️⃣ Actualizar o agregar canchas
        for (var fieldReq : venueRequest.getFields()) {
            if (fieldReq.getId() != null) {
                // UPDATE cancha existente
                Field existingField = existingVenue.getFields().stream()
                        .filter(f -> f.getId().equals(fieldReq.getId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Field con ID " + fieldReq.getId() + " no encontrado"));
                existingField.setName(fieldReq.getName());
                existingField.setFieldType(fieldReq.getFieldType());
                existingField.setHourlyRate(fieldReq.getHourlyRate());
            } else {
                // INSERT nueva cancha
                Field newField = new Field();
                newField.setName(fieldReq.getName());
                newField.setFieldType(fieldReq.getFieldType());
                newField.setHourlyRate(fieldReq.getHourlyRate());
                newField.setVenue(existingVenue);
                existingVenue.getFields().add(newField);
            }
        }

        // 6️⃣ Guardar todo
        Venue updatedVenue = venueRepositoryPort.save(existingVenue);

        return VenueModelMapper.toDTO(updatedVenue);
    }



    @Override
    public boolean existsByAdminId(Long adminId) {
        return venueRepositoryPort.existsByAdminId(adminId);
    }
}
