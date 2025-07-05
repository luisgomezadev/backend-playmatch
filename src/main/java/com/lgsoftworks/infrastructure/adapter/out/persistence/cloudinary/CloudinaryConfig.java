package com.lgsoftworks.infrastructure.adapter.out.persistence.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "db5gphaih",
                "api_key", "352858457577417",
                "api_secret", "vIx5mexlS2EVNrHTQElmUTo7JQc",
                "secure", true
        ));
    }
}
