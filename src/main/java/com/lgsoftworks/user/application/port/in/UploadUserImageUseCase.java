package com.lgsoftworks.user.application.port.in;

import com.lgsoftworks.user.application.dto.response.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadUserImageUseCase {
    UserDTO uploadUserImage(Long userId, MultipartFile imageFile);
}
