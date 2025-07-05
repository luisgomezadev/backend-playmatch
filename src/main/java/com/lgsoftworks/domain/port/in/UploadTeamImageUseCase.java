package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.TeamDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadTeamImageUseCase {
    TeamDTO uploadTeamImage(Long teamId, MultipartFile imageFile);
}
