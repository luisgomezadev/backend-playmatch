package com.lgsoftworks.application.service;

import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.exception.RequestPlayerByIdNotFoundException;
import com.lgsoftworks.domain.model.RequestPlayer;
import com.lgsoftworks.domain.port.in.AssignTeamUseCase;
import com.lgsoftworks.domain.port.in.HandleRequestUseCase;
import com.lgsoftworks.domain.port.out.RequestPlayerRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HandleRequestService implements HandleRequestUseCase {

    private final RequestPlayerRepositoryPort requestPlayerRepositoryPort;
    private final AssignTeamUseCase assignTeamUseCase;

    @Override
    public void handleRequest(StatusRequest statusRequest, Long id) {
        RequestPlayer requestPlayer = requestPlayerRepositoryPort.findById(id)
                .orElseThrow(() -> new RequestPlayerByIdNotFoundException(id));

        requestPlayer.setStatusRequest(statusRequest);

        if (requestPlayer.getStatusRequest() == StatusRequest.ACCEPTED) {
            assignTeamUseCase.addMemberToTeam(requestPlayer.getTeam().getId(),
                    requestPlayer.getPlayer().getId());
        }

        requestPlayerRepositoryPort.save(requestPlayer);
    }
}
