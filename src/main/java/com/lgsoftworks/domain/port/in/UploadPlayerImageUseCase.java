package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadPlayerImageUseCase {
    UserDTO uploadPlayerImage(Long userId, MultipartFile imageFile);
}
