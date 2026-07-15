package com.lgsoftworks.venue.application.port.in;

import com.lgsoftworks.common.response.PageResponse;
import com.lgsoftworks.venue.application.dto.request.VenueFilter;
import com.lgsoftworks.venue.application.dto.response.VenuePublicDTO;
import org.springframework.data.domain.Pageable;

public interface VenuePublicUseCase {
    VenuePublicDTO findById(Long id);
    VenuePublicDTO findByCode(String code);
    PageResponse<VenuePublicDTO> searchVenues(VenueFilter filter, Pageable pageable);
}
