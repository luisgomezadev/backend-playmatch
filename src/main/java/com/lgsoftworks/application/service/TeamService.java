package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.TeamModelMapper;
import com.lgsoftworks.application.dto.summary.TeamSummaryDTO;
import com.lgsoftworks.application.mapper.UserModelMapper;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.port.in.AssignTeamUseCase;
import com.lgsoftworks.domain.port.in.TeamUseCase;
import com.lgsoftworks.application.dto.TeamDTO;
import com.lgsoftworks.application.dto.request.TeamRequest;
import com.lgsoftworks.domain.exception.*;
import com.lgsoftworks.domain.model.Team;
import com.lgsoftworks.domain.port.in.UploadTeamImageUseCase;
import com.lgsoftworks.domain.port.out.CloudinaryImageUploaderPort;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.domain.port.out.TeamRepositoryPort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService implements TeamUseCase, UploadTeamImageUseCase {

    private final TeamRepositoryPort teamRepositoryPort;
    private final PlayerRepositoryPort playerRepositoryPort;
    private final CloudinaryImageUploaderPort imageUploader;
    private final AssignTeamUseCase assignTeamUseCase;

    @Override
    public List<TeamDTO> findAll() {
        List<Team> teamList = teamRepositoryPort.findAll();
        return teamList.stream()
                .map(TeamModelMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<TeamDTO> findById(Long id) {
        Optional<Team> optionalTeam = teamRepositoryPort.findById(id);
        return optionalTeam.map(TeamModelMapper::toDTO);
    }

    @Override
    public TeamSummaryDTO save(TeamRequest teamRequest) {
        Player player = validateOwner(teamRequest);
        Team team = TeamModelMapper.toModelRequest(teamRequest);
        Team savedTeam = teamRepositoryPort.save(team);
        assignTeamUseCase.addMemberToTeam(savedTeam.getId(), player.getId());
        savedTeam.getMembers().add(player);
        return TeamModelMapper.toTeamSummary(savedTeam);
    }

    @Override
    public TeamSummaryDTO update(TeamRequest teamRequest) {
        Long teamId = teamRequest.getId();
        Team existingTeam = teamRepositoryPort.findById(teamId)
                .orElseThrow(() -> new TeamByIdNotFoundException(teamId));

        teamRequest.setId(teamId);
        teamRequest.setOwnerId(existingTeam.getOwnerId());
        validateOwnerForUpdate(teamRequest);
        validMembers(existingTeam, teamRequest.getMaxPlayers());
        Team team = TeamModelMapper.toModelRequest(teamRequest);
        Team updateTeam = teamRepositoryPort.save(team);
        return TeamModelMapper.toTeamSummary(updateTeam);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Team team = teamRepositoryPort.findById(id)
                .orElseThrow(() -> new TeamByIdNotFoundException(id));
        for (Player player : team.getMembers()){
            player.setTeam(null);
        }
        playerRepositoryPort.saveAll(team.getMembers());
        return teamRepositoryPort.deleteById(id);
    }

    @Override
    public boolean existsByOwnerId(Long ownerId) {
        return teamRepositoryPort.existsByOwnerId(ownerId);
    }

    @Override
    public List<TeamDTO> findByCity(String city) {
        List<Team> teamList = teamRepositoryPort.findByCity(city);
        return teamList.stream()
                .map(TeamModelMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<TeamDTO> findByOwnerId(Long id) {
        Optional<Team> optionalTeam = teamRepositoryPort.findByOwnerId(id);
        return optionalTeam.map(TeamModelMapper::toDTO);
    }

    public Player validateOwner(TeamRequest teamRequest) {
        Player player = playerRepositoryPort.findById(teamRequest.getOwnerId())
                .orElseThrow(() -> new UserByIdNotFoundException(teamRequest.getOwnerId()));

        if (teamRepositoryPort.existsByOwnerId(player.getId())) {
            throw new DuplicateOwnerException(player);
        }

        return player;
    }

    private void validMembers(Team team, Integer maxPlayers) {
        int membersSize = team.getMembers().size();
        if (maxPlayers < membersSize) {
            throw new TeamCapacityBelowCurrentPlayersException(membersSize, maxPlayers);
        }
    }

    private void validateOwnerForUpdate(TeamRequest teamRequest) {
        Player player = playerRepositoryPort.findById(teamRequest.getOwnerId())
                .orElseThrow(() -> new UserByIdNotFoundException(teamRequest.getOwnerId()));

        Optional<Team> existingTeamWithOwner = teamRepositoryPort.findByOwnerId(player.getId());

        if (existingTeamWithOwner.isPresent()) {
            // Si existe un equipo con este dueño y no es el mismo equipo que estamos actualizando
            if (!existingTeamWithOwner.get().getId().equals(teamRequest.getId())) {
                throw new DuplicateOwnerException(player);
            }
        }
    }

    @Override
    public TeamDTO uploadTeamImage(Long teamId, MultipartFile imageFile) {
        String imageUrl = imageUploader.uploadImage(imageFile);

        Team team = teamRepositoryPort.findById(teamId)
                .orElseThrow(() -> new TeamByIdNotFoundException(teamId));

        team.setImageUrl(imageUrl);
        teamRepositoryPort.save(team);
        return TeamModelMapper.toDTO(team);
    }
}
