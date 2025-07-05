package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.UserModelMapper;
import com.lgsoftworks.application.mapper.PlayerModelMapper;
import com.lgsoftworks.application.dto.PlayerDTO;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.domain.exception.UserByCellphoneNotFoundException;
import com.lgsoftworks.domain.exception.UserByDocumentNotFoundException;
import com.lgsoftworks.domain.exception.UserByEmailNotFoundException;
import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
import com.lgsoftworks.domain.port.in.UploadAdminImageUseCase;
import com.lgsoftworks.domain.port.in.UploadPlayerImageUseCase;
import com.lgsoftworks.domain.port.out.CloudinaryImageUploaderPort;
import com.lgsoftworks.domain.port.out.FieldAdminRepositoryPort;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.domain.validation.ValidateUser;
import com.lgsoftworks.application.dto.summary.PlayerSummaryDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements PlayerUseCase, UploadPlayerImageUseCase {

    private final PlayerRepositoryPort playerRepositoryPort;
    private final CloudinaryImageUploaderPort imageUploader;
    private final ValidateUser validateUser;

    private final Path uploadDir = Paths.get("uploads");

    public PlayerService(PlayerRepositoryPort playerRepositoryPort, FieldAdminRepositoryPort fieldAdminRepositoryPort,
                         CloudinaryImageUploaderPort cloudinaryImageUploaderPort) {
        this.playerRepositoryPort = playerRepositoryPort;
        this.imageUploader = cloudinaryImageUploaderPort;
        this.validateUser = new ValidateUser(fieldAdminRepositoryPort, playerRepositoryPort);
    }

    @Override
    public List<PlayerSummaryDTO> findAll() {
        List<Player> playerList = playerRepositoryPort.findAll();
        return playerList.stream()
                .map(PlayerModelMapper::toSummaryDTO)
                .toList();
    }

    @Override
    public Optional<PlayerSummaryDTO> findById(Long id) {
        Optional<Player> optionalPlayer = playerRepositoryPort.findById(id);
        return optionalPlayer.map(PlayerModelMapper::toSummaryDTO);
    }

    @Override
    public UserDTO save(UserRequest playerRequest) {
        validateUser.validate(playerRequest.getDocumentNumber(),
                playerRequest.getEmail(), playerRequest.getCellphone());
        Player savedUser = playerRepositoryPort.save(PlayerModelMapper.toModelRequest(playerRequest));
        return UserModelMapper.toUserDTO(savedUser);
    }

    @Override
    public List<UserDTO> saveAll(List<UserRequest> playerRequests) {
        for (UserRequest p : playerRequests) {
            validateUser.validate(p.getDocumentNumber(), p.getEmail(), p.getCellphone());
        }

        List<Player> players = playerRequests.stream()
                .map(PlayerModelMapper::toModelRequest)
                .toList();

        List<Player> savedPlayers = playerRepositoryPort.saveAll(players);

        return savedPlayers.stream()
                .map(UserModelMapper::toUserDTO)
                .toList();
    }

    @Override
    public UserDTO update(UserRequest playerRequest) {
        validateUser.validate(playerRequest.getDocumentNumber(),
                playerRequest.getEmail(), playerRequest.getCellphone());
        Player updatedPlayer = playerRepositoryPort.save(PlayerModelMapper.toModelRequest(playerRequest));
        return UserModelMapper.toUserDTO(updatedPlayer);
    }

    @Override
    public boolean deleteById(Long id) {
        return playerRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<PlayerDTO> findByDocumentNumber(String documentNumber) {
        Optional<Player> optionalPlayer = playerRepositoryPort.findByDocumentNumber(documentNumber);
        if (optionalPlayer.isEmpty()) {
            throw new UserByDocumentNotFoundException(documentNumber);
        }
        return optionalPlayer.map(PlayerModelMapper::toDTO);
    }

    @Override
    public Optional<PlayerDTO> findByEmail(String email) {
        Optional<Player> optionalPlayer = playerRepositoryPort.findByEmail(email);
        if (optionalPlayer.isEmpty()) {
            throw new UserByEmailNotFoundException(email);
        }
        return optionalPlayer.map(PlayerModelMapper::toDTO);
    }

    @Override
    public Optional<PlayerDTO> findByCellphone(String cellphone) {
        Optional<Player> optionalPlayer = playerRepositoryPort.findByCellphone(cellphone);
        if (optionalPlayer.isEmpty()) {
            throw new UserByCellphoneNotFoundException(cellphone);
        }
        return optionalPlayer.map(PlayerModelMapper::toDTO);
    }

    @Override
    public boolean existsByIdAndTeamId(Long playerId, Long teamId) {
        return playerRepositoryPort.existsByIdAndTeamId(playerId, teamId);
    }

    @Override
    public List<PlayerSummaryDTO> findAllByTeamId(Long teamId) {
        List<Player> playerList = playerRepositoryPort.findAllByTeamId(teamId);
        return playerList.stream()
                .map(PlayerModelMapper::toSummaryDTO)
                .toList();
    }

    @Override
    public UserDTO uploadPlayerImage(Long userId, MultipartFile imageFile) {
        String imageUrl = imageUploader.uploadImage(imageFile);

        Player player = playerRepositoryPort.findById(userId)
                .orElseThrow(() -> new UserByIdNotFoundException(userId));

        player.setImageUrl(imageUrl);
        playerRepositoryPort.save(player);
        return UserModelMapper.toUserDTO(player);
    }

}
