package com.lgsoftworks.user.domain.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploaderPort {
    String uploadImage(MultipartFile file);
}
