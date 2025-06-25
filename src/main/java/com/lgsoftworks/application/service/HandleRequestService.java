package com.lgsoftworks.application.service;

import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.exception.RequestPlayerByIdNotFoundException;
import com.lgsoftworks.domain.model.TeamApplication;
import com.lgsoftworks.domain.port.in.AssignTeamUseCase;
import com.lgsoftworks.domain.port.in.HandleRequestUseCase;
import com.lgsoftworks.domain.port.out.TeamApplicationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HandleRequestService implements HandleRequestUseCase {

    private final TeamApplicationRepositoryPort teamApplicationRepositoryPort;
    private final AssignTeamUseCase assignTeamUseCase;

    @Override
    public void handleRequest(StatusRequest statusRequest, Long id) {
        TeamApplication teamApplication = teamApplicationRepositoryPort.findById(id)
                .orElseThrow(() -> new RequestPlayerByIdNotFoundException(id));

        teamApplication.setStatusRequest(statusRequest);

        if (teamApplication.getStatusRequest() == StatusRequest.ACCEPTED) {
            assignTeamUseCase.addMemberToTeam(teamApplication.getTeam().getId(),
                    teamApplication.getPlayer().getId());
        }

        teamApplicationRepositoryPort.save(teamApplication);
    }
}
