package com.lgsoftworks.domain.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageUploaderPort {
    String uploadImage(MultipartFile file);
}
