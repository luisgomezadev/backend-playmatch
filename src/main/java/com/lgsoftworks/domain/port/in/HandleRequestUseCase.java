package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.domain.enums.StatusRequest;

public interface HandleRequestUseCase {
    void handleRequest(StatusRequest statusRequest, Long id);
}
