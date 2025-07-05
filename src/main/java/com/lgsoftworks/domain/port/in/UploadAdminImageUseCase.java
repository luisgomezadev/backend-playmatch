package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadAdminImageUseCase {
    UserDTO uploadAdminImage(Long userId, MultipartFile imageFile);
}
