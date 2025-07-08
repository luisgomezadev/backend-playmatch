package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.PlayerDTO;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.application.dto.summary.PlayerSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PlayerUseCase {
    Page<PlayerSummaryDTO> findAll(Pageable pageable);
    Optional<PlayerSummaryDTO> findById(Long id);
    UserDTO save(UserRequest playerRequest);
    List<UserDTO> saveAll(List<UserRequest> playerRequests);
    UserDTO update(UserRequest playerRequest);
    boolean deleteById(Long id);
    Optional<PlayerDTO> findByDocumentNumber(String documentNumber);
    Optional<PlayerDTO> findByEmail(String email);
    Optional<PlayerDTO> findByCellphone(String cellphone);
    boolean existsByIdAndTeamId(Long playerId, Long teamId);
    List<PlayerSummaryDTO> findAllByTeamId(Long teamId);
}
