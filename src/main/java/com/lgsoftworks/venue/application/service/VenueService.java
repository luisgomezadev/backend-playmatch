package com.lgsoftworks.venue.application.service;

import com.lgsoftworks.venue.application.dto.mapper.VenueModelMapper;
import com.lgsoftworks.venue.application.dto.request.VenueRequest;
import com.lgsoftworks.venue.application.dto.response.VenueDTO;
import com.lgsoftworks.venue.application.port.in.VenueUseCase;
import com.lgsoftworks.venue.domain.exception.VenueByCodeNotFoundException;
import com.lgsoftworks.venue.domain.exception.VenueByIdNotFoundException;
import com.lgsoftworks.venue.domain.model.Venue;
import com.lgsoftworks.venue.domain.port.out.VenueRepositoryPort;
import com.lgsoftworks.venue.domain.service.VenueUniquenessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VenueService implements VenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;
    private final VenueUniquenessValidator venueUniquenessValidator;
    private final VenueModelMapper venueModelMapper;

    @Override
    public VenueDTO save(VenueRequest request) {
        venueUniquenessValidator.validate(request.getCode());

        Venue venue = Venue.create(
                request.getCode(),
                request.getName(),
                request.getCity(),
                request.getAddress(),
                request.getOpeningHour(),
                request.getClosingHour(),
                request.getAdminId()
        );
        Venue saved = venueRepositoryPort.save(venue);
        return venueModelMapper.toDTO(saved);
    }

    @Override
    public VenueDTO update(Long id, VenueRequest request) {
        Venue venue = venueRepositoryPort.findById(id)
                .orElseThrow(() -> new VenueByIdNotFoundException(id));

        venueUniquenessValidator.validateForUpdate(request.getCode(), id);

        venue.rename(request.getName());
        venue.changeCode(request.getCode());
        venue.changeAddress(request.getCity(), request.getAddress());
        venue.changeSchedule(request.getOpeningHour(), request.getClosingHour());

        Venue saved = venueRepositoryPort.save(venue);
        return venueModelMapper.toDTO(saved);
    }

    @Override
    public VenueDTO findById(Long id) {
        return venueRepositoryPort.findById(id)
                .map(venueModelMapper::toDTO)
                .orElseThrow(() -> new VenueByIdNotFoundException(id));
    }

    @Override
    public VenueDTO findByCode(String code) {
        return venueRepositoryPort.findByCode(code)
                .map(venueModelMapper::toDTO)
                .orElseThrow(() -> new VenueByCodeNotFoundException(code));
    }

    @Override
    public Optional<VenueDTO> findByAdminId(Long adminId) {
        return venueRepositoryPort.findByAdminId(adminId).map(venueModelMapper::toDTO);
    }

    @Override
    public void deleteById(Long id) {
        venueRepositoryPort.findById(id).orElseThrow(() -> new VenueByIdNotFoundException(id));
        venueRepositoryPort.deleteById(id);
    }
}