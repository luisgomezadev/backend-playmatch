package com.lgsoftworks.venue.application.service;

import com.lgsoftworks.auth.application.service.CurrentUserService;
import com.lgsoftworks.auth.domain.exception.AccessDeniedException;
import com.lgsoftworks.common.response.PageResponse;
import com.lgsoftworks.user.domain.model.User;
import com.lgsoftworks.venue.application.dto.mapper.VenueModelMapper;
import com.lgsoftworks.venue.application.dto.request.VenueFilter;
import com.lgsoftworks.venue.application.dto.request.VenueRequest;
import com.lgsoftworks.venue.application.dto.response.VenueDTO;
import com.lgsoftworks.venue.application.port.in.VenueUseCase;
import com.lgsoftworks.venue.domain.exception.VenueByCodeNotFoundException;
import com.lgsoftworks.venue.domain.exception.VenueByIdNotFoundException;
import com.lgsoftworks.venue.domain.model.Venue;
import com.lgsoftworks.venue.domain.port.out.VenueRepositoryPort;
import com.lgsoftworks.venue.domain.service.VenueUniquenessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VenueService implements VenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;
    private final VenueUniquenessValidator venueUniquenessValidator;
    private final VenueModelMapper venueModelMapper;
    private final CurrentUserService currentUserService;

    @Override
    public VenueDTO save(VenueRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        venueUniquenessValidator.validate(request.getCode());

        Venue venue = Venue.create(
                request.getCode(),
                request.getName(),
                request.getCity(),
                request.getAddress(),
                request.getOpeningHour(),
                request.getClosingHour(),
                currentUser.getId()
        );
        Venue saved = venueRepositoryPort.save(venue);
        return venueModelMapper.toDTO(saved);
    }

    @Override
    public VenueDTO update(Long id, VenueRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        Venue venue = venueRepositoryPort.findById(id)
                .orElseThrow(() -> new VenueByIdNotFoundException(id));

        if (!venue.getAdminId().equals(currentUser.getId())) {
            throw new AccessDeniedException("No tienes permiso para modificar este complejo deportivo");
        }

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
        User currentUser = currentUserService.getCurrentUser();

        Venue venue = venueRepositoryPort.findById(id)
                .orElseThrow(() -> new VenueByIdNotFoundException(id));

        if (!venue.getAdminId().equals(currentUser.getId())) {
            throw new AccessDeniedException("No tienes permiso para ver este complejo deportivo");
        }

        return venueModelMapper.toDTO(venue);
    }

    @Override
    public VenueDTO findByCode(String code) {
        return venueRepositoryPort.findByCode(code)
                .map(venueModelMapper::toDTO)
                .orElseThrow(() -> new VenueByCodeNotFoundException(code));
    }

    @Override
    public Optional<VenueDTO> findByAdminId() {
        User currentUser = currentUserService.getCurrentUser();
        return venueRepositoryPort.findByAdminId(currentUser.getId()).map(venueModelMapper::toDTO);
    }

    @Override
    public void deleteById(Long id) {
        User currentUser = currentUserService.getCurrentUser();

        Venue venue = venueRepositoryPort.findById(id)
                .orElseThrow(() -> new VenueByIdNotFoundException(id));

        if (!venue.getAdminId().equals(currentUser.getId())) {
            throw new AccessDeniedException("No tienes permiso para eliminar este complejo deportivo");
        }

        venueRepositoryPort.deleteById(id);
    }

    @Override
    public PageResponse<VenueDTO> searchVenues(VenueFilter filter, Pageable pageable) {
        return PageResponse.from(
                venueRepositoryPort.search(filter.name(), filter.city(), pageable)
                        .map(venueModelMapper::toDTO)
        );
    }
}