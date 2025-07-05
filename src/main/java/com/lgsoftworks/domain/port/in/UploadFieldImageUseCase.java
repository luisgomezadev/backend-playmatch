package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.FieldDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFieldImageUseCase {
    FieldDTO uploadFieldImage(Long fieldId, MultipartFile imageFile);
}
