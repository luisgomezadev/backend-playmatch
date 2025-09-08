package com.lgsoftworks.domain.field.port.in;

import com.lgsoftworks.application.field.dto.response.FieldDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFieldImageUseCase {
    FieldDTO uploadFieldImage(Long fieldId, MultipartFile imageFile);
}
