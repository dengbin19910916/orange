package com.geek.orange.api.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

/**
 * 证件对象，用于上传证件。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    /**
     * 证件图片名称。
     */
    private String name;
    /**
     * 证件图片Base64编码数据。
     */
    private String data;

    public Passport(MultipartFile file) throws IOException {
        this.name = file.getOriginalFilename();
        this.data = Base64.getEncoder().encodeToString(file.getBytes());
    }
}
