package com.geek.orange.product.site.app;

import com.geek.orange.api.app.dto.Passport;
import com.geek.orange.api.app.service.PassportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Api(tags = "证件处理")
@RestController
public class PassportController implements PassportService {

    private static final Logger log = LogManager.getLogger(PassportController.class);

    private static final Base64.Decoder DECODER = Base64.getDecoder();

    @Value("${passport.image.directory}")
    private String imageDir;

    @ApiOperation("查询所有的证件图片")
    @Override
    public Passport[] search(@RequestParam("reqId") Long reqId) {
        System.err.println("request id = " + reqId);
        return new Passport[]{new Passport("one.jpg", "123456")};
    }

    @ApiOperation("保存所有的证件图片")
    @Override
    public void save(@RequestBody Passport[] passports) {
        for (Passport passport : passports) {
            if (passport.getData() != null) {
                byte[] data = DECODER.decode(passport.getData());
                try {
                    Files.write(Paths.get(imageDir, passport.getName()), data);
                } catch (IOException e) {
                    log.error("文件无法保存", e);
                }
            }
        }
    }
}
