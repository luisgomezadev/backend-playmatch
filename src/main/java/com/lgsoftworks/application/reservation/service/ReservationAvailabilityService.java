package com.lgsoftworks.application.reservation.service;

import com.lgsoftworks.application.reservation.dto.response.ReservationDTO;
import com.lgsoftworks.application.reservation.dto.response.TimeSlot;
import com.lgsoftworks.application.reservation.dto.mapper.ReservationModelMapper;
import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.common.util.GenerateCodeUtil;
import com.lgsoftworks.domain.exception.FieldByIdNotFoundException;
import com.lgsoftworks.domain.exception.VenueByIdNotFoundException;
import com.lgsoftworks.application.reservation.dto.request.ReservationRequest;
import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.field.port.out.FieldRepositoryPort;
import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.domain.reservation.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.reservation.port.out.ReservationRepositoryPort;
import com.lgsoftworks.domain.reservation.validation.ValidateReservation;
import com.lgsoftworks.domain.venue.model.Venue;
import com.lgsoftworks.domain.venue.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationAvailabilityService implements ReservationAvailabilityUseCase {

    private final VenueRepositoryPort venueRepositoryPort;
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final FieldRepositoryPort fieldRepositoryPort;

    @Override
    public List<TimeSlot> getAvailableSlots(Long venueId, Long fieldId, LocalDate date) {
        Venue venue = venueRepositoryPort.findById(venueId)
                .orElseThrow(() -> new VenueByIdNotFoundException(venueId));

        LocalTime open = venue.getOpeningHour();
        LocalTime close = venue.getClosingHour();

        List<Reservation> reservations = reservationRepositoryPort.findByFieldIdAndDate(fieldId, date);

        List<TimeSlot> available = new ArrayList<>();
        LocalTime current = open;

        for (Reservation res : reservations) {
            if (current.isBefore(res.getStartTime())) {
                available.add(new TimeSlot(current, res.getStartTime()));
            }
            current = res.getEndTime();
        }

        if (current.isBefore(close)) {
            available.add(new TimeSlot(current, close));
        }

        return available;
    }

    @Override
    public Optional<ReservationDTO> reservationAvailability(ReservationRequest reservationRequest) {
        Field field = fieldRepositoryPort.findById(reservationRequest.getFieldId())
                .orElseThrow(() -> new FieldByIdNotFoundException(reservationRequest.getFieldId()));

        LocalTime time = reservationRequest.getStartTime().plusMinutes(reservationRequest.getDuration().getMinutes());

        Reservation reservation = ReservationModelMapper.toModelRequest(reservationRequest);
        reservation.setEndTime(time);
        reservation.setField(field);
        reservation.setCode(GenerateCodeUtil.generateCode(6));
        reservation.setStatus(Status.ACTIVE);

        ValidateReservation.validateTimeWithinVenueSchedule(reservation, field.getVenue());
        ValidateReservation.validateFieldAvailability(reservation, field);

        return Optional.of(ReservationModelMapper.toDTO(reservation));
    }
}
