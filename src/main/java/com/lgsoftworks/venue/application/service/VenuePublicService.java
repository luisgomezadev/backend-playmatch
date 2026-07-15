package com.lgsoftworks.venue.application.service;

import com.lgsoftworks.common.response.PageResponse;
import com.lgsoftworks.venue.application.dto.mapper.VenueModelMapper;
import com.lgsoftworks.venue.application.dto.request.VenueFilter;
import com.lgsoftworks.venue.application.dto.response.VenuePublicDTO;
import com.lgsoftworks.venue.application.port.in.VenuePublicUseCase;
import com.lgsoftworks.venue.domain.exception.VenueByCodeNotFoundException;
import com.lgsoftworks.venue.domain.exception.VenueByIdNotFoundException;
import com.lgsoftworks.venue.domain.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenuePublicService implements VenuePublicUseCase{

    private final VenueRepositoryPort venueRepositoryPort;

    @Override
    public VenuePublicDTO findById(Long id) {
        return venueRepositoryPort.findById(id)
                .map(VenueModelMapper::toPublicDTO)
                .orElseThrow(() -> new VenueByIdNotFoundException(id));
    }

    @Override
    public VenuePublicDTO findByCode(String code) {
        return venueRepositoryPort.findByCode(code)
                .map(VenueModelMapper::toPublicDTO)
                .orElseThrow(() -> new VenueByCodeNotFoundException(code));
    }

    @Override
    public PageResponse<VenuePublicDTO> searchVenues(VenueFilter filter, Pageable pageable) {
        return PageResponse.from(
                venueRepositoryPort.search(filter.name(), filter.city(), pageable)
                        .map(VenueModelMapper::toPublicDTO)
        );
    }
}
