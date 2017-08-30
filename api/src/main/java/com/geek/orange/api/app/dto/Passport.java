package com.geek.orange.api.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    private String name;
    private String data;

    public Passport(MultipartFile file) throws IOException {
        this.name = file.getOriginalFilename();
        this.data = Base64.getEncoder().encodeToString(file.getBytes());
    }
}
