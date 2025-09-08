package com.lgsoftworks.application.reservation.service;

import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.reservation.dto.request.ReservationFilter;
import com.lgsoftworks.application.reservation.dto.mapper.ReservationModelMapper;
import com.lgsoftworks.application.reservation.dto.response.ReservationDTO;
import com.lgsoftworks.application.reservation.dto.request.ReservationRequest;
import com.lgsoftworks.application.reservation.dto.response.ReservationAvailabilityDTO;
import com.lgsoftworks.domain.reservation.enums.StatusReservation;
import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.domain.reservation.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.reservation.port.in.ReservationUseCase;
import com.lgsoftworks.domain.reservation.port.out.ReservationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationUseCase {

    private final ReservationRepositoryPort reservationRepositoryPort;
    private final ReservationAvailabilityUseCase reservationAvailabilityUseCase;

    @Override
    public PageResponse<ReservationDTO> searchReservations(ReservationFilter filter, Pageable pageable) {
        Sort sort = Sort.by(Sort.Order.desc("reservationDate"), Sort.Order.desc("startTime"));
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<ReservationDTO> reservationDTOS = reservationRepositoryPort.searchReservations(filter, sortedPageable)
                .map(ReservationModelMapper::toDTO);

        return new PageResponse<>(
                reservationDTOS.getContent(),
                reservationDTOS.getNumber(),
                reservationDTOS.getSize(),
                reservationDTOS.getTotalElements(),
                reservationDTOS.getTotalPages(),
                reservationDTOS.isLast()
        );
    }

    @Override
    public Optional<ReservationDTO> findById(Long id) {
        Optional<Reservation> reservation = reservationRepositoryPort.findById(id);
        return reservation.map(ReservationModelMapper::toDTO);
    }

    @Override
    public ReservationDTO save(ReservationRequest reservationRequest) {

        Optional<ReservationAvailabilityDTO> reservationDTO = reservationAvailabilityUseCase.reservationAvailability(reservationRequest);

        if (reservationDTO.isPresent()) {
            Reservation reservation = ReservationModelMapper.toModelOfAvailability(reservationDTO.get());

            Reservation savedReservation = reservationRepositoryPort.save(reservation);
            return ReservationModelMapper.toDTO(savedReservation);
        }
        return null;
    }

    @Override
    public ReservationDTO update(ReservationRequest reservationRequest) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        reservationRepositoryPort.deleteById(id);
    }

    @Override
    public void updateStatus(Long id, StatusReservation status) {
        reservationRepositoryPort.updateStatus(id, status);
    }

    @Override
    public List<ReservationDTO> findByFieldId(Long fieldId) {
        List<Reservation> reservationList = reservationRepositoryPort.findByFieldId(fieldId);
        return reservationList.stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate).reversed()
                        .thenComparing(Reservation::getStartTime))
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReservationDTO> findByFieldIdAndStatus(Long fieldId, StatusReservation status) {
        List<Reservation> reservationList = reservationRepositoryPort.findByFieldIdAndStatus(fieldId, status);
        return reservationList.stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate).reversed()
                        .thenComparing(Reservation::getStartTime))
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReservationDTO> findByUserId(Long userId) {
        List<Reservation> reservationList = reservationRepositoryPort.findByUserId(userId);
        return reservationList.stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate).reversed()
                        .thenComparing(Reservation::getStartTime))
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReservationDTO> findLastThreeReservations(Long fieldId) {
        List<Reservation> reservationList = reservationRepositoryPort.findLastThreeReservations(fieldId);
        return reservationList.stream()
                .map(ReservationModelMapper::toDTO)
                .toList();
    }

}
