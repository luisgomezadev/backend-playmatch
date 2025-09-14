package com.lgsoftworks.domain.venue.port.in;

import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.venue.dto.request.VenueFilter;
import com.lgsoftworks.application.venue.dto.request.VenueRequest;
import com.lgsoftworks.application.venue.dto.response.VenueDTO;
import com.lgsoftworks.domain.venue.model.Venue;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VenueUseCase {
    Optional<VenueDTO> findById(Long id);

    Optional<VenueDTO> findByAdminId(Long adminId);

    Optional<VenueDTO> findByCode(String code);

    PageResponse<VenueDTO> searchVenues(VenueFilter filter, Pageable pageable);

    VenueDTO save(VenueRequest venueRequest);

    VenueDTO update(VenueRequest venueRequest);

    boolean existsByAdminId(Long adminId);
}
