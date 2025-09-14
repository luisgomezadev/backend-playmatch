package com.lgsoftworks.application.venue.service;

import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.field.dto.mapper.FieldModelMapper;
import com.lgsoftworks.application.venue.dto.mapper.VenueModelMapper;
import com.lgsoftworks.application.venue.dto.request.VenueFilter;
import com.lgsoftworks.application.venue.dto.request.VenueRequest;
import com.lgsoftworks.application.venue.dto.response.VenueDTO;
import com.lgsoftworks.domain.common.util.GenerateCodeUtil;
import com.lgsoftworks.domain.exception.UserAlreadyAssignedAsAdminException;
import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
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

import java.util.List;
import java.util.Optional;

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
        venue.setCode(GenerateCodeUtil.generateCode(10));

        List<Field> fields = venueRequest.getFields().stream()
                .map(FieldModelMapper::toModelRequest)
                .peek(f -> f.setVenue(venue))
                .toList();

        venue.setFields(fields);

        Venue savedVenue = venueRepositoryPort.save(venue);
        return VenueModelMapper.toDTO(savedVenue);
    }

    @Override
    public VenueDTO update(VenueRequest venueRequest) {
        return null;
    }

    @Override
    public boolean existsByAdminId(Long adminId) {
        return venueRepositoryPort.existsByAdminId(adminId);
    }
}
