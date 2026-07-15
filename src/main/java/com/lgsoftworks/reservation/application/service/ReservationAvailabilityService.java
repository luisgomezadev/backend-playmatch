package com.lgsoftworks.reservation.application.service;

import com.lgsoftworks.field.domain.exception.FieldByIdNotFoundException;
import com.lgsoftworks.field.domain.model.Field;
import com.lgsoftworks.field.domain.port.out.FieldRepositoryPort;
import com.lgsoftworks.reservation.application.dto.mapper.ReservationModelMapper;
import com.lgsoftworks.reservation.application.dto.request.ReservationRequest;
import com.lgsoftworks.reservation.application.dto.response.ReservationDTO;
import com.lgsoftworks.reservation.application.dto.response.TimeSlot;
import com.lgsoftworks.reservation.application.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.reservation.domain.model.Reservation;
import com.lgsoftworks.reservation.domain.port.out.ReservationRepositoryPort;
import com.lgsoftworks.reservation.domain.service.ValidateReservation;
import com.lgsoftworks.reservation.domain.util.GenerateCodeUtil;
import com.lgsoftworks.venue.domain.exception.VenueByIdNotFoundException;
import com.lgsoftworks.venue.domain.model.Venue;
import com.lgsoftworks.venue.domain.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationAvailabilityService implements ReservationAvailabilityUseCase {

    private static final int CODE_LENGTH = 6;

    private final VenueRepositoryPort venueRepositoryPort;
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final FieldRepositoryPort fieldRepositoryPort;
    private final ValidateReservation validateReservation;
    private final ReservationModelMapper reservationModelMapper;

    @Override
    public List<TimeSlot> getAvailableSlots(Long venueId, Long fieldId, LocalDate date) {
        Venue venue = venueRepositoryPort.findById(venueId)
                .orElseThrow(() -> new VenueByIdNotFoundException(venueId));

        LocalTime open = venue.getOpeningHour();
        LocalTime close = venue.getClosingHour();

        List<Reservation> reservations = reservationRepositoryPort.findActiveByFieldIdAndDate(fieldId, date);

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
    @Transactional
    public ReservationDTO reservationAvailability(ReservationRequest request) {
        Field field = fieldRepositoryPort.findById(request.getFieldId())
                .orElseThrow(() -> new FieldByIdNotFoundException(request.getFieldId()));

        Venue venue = venueRepositoryPort.findById(field.getVenueId().value())
                .orElseThrow(() -> new VenueByIdNotFoundException(field.getVenueId().value()));

        List<Reservation> existingReservations = reservationRepositoryPort.findActiveByFieldIdAndDate(
                field.getId(), request.getReservationDate());

        Reservation reservation = Reservation.create(
                GenerateCodeUtil.generateCode(CODE_LENGTH),
                request.getCustomerName(),
                request.getCellphone(),
                field.getId(),
                request.getReservationDate(),
                request.getStartTime(),
                request.getDuration()
        );

        validateReservation.validateTimeWithinVenueSchedule(reservation, venue);
        validateReservation.validateFieldAvailability(reservation, existingReservations);

        Reservation saved = reservationRepositoryPort.save(reservation);
        return reservationModelMapper.toDTO(saved);
    }
}