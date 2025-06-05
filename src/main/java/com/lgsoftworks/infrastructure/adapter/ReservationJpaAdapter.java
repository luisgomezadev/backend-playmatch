package com.lgsoftworks.infrastructure.adapter;

import com.lgsoftworks.domain.model.Reservation;
import com.lgsoftworks.domain.port.out.ReservationRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.entity.ReservationEntity;
import com.lgsoftworks.infrastructure.adapter.mapper.ReservationDboMapper;
import com.lgsoftworks.infrastructure.adapter.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReservationJpaAdapter implements ReservationRepositoryPort {

    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationDboMapper::toModel)
                .toList();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findById(id);
        return optionalReservation.map(ReservationDboMapper::toModel);
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity savedReservation = reservationRepository.save(ReservationDboMapper.toDbo(reservation));
        return ReservationDboMapper.toModel(savedReservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
