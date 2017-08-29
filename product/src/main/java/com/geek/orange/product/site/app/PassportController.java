package com.geek.orange.product.site.app;

import com.geek.orange.api.app.dto.Passport;
import com.geek.orange.api.app.service.PassportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Base64;

@Api(tags = "证件处理")
@RestController
public class PassportController implements PassportService {

    private static final Base64.Decoder DECODER = Base64.getDecoder();

    @ApiOperation("查询所有的证件图片")
    @Override
    public Passport[] search(@RequestParam("reqId") Long reqId) {
        System.err.println("request id = " + reqId);
        return new Passport[]{new Passport("one.jpg", "123456")};
    }

    @ApiOperation("保存所有的证件图片")
    @Override
    public void save(@RequestBody Passport[] passports) {
        System.err.println("passports = " + Arrays.toString(passports));
        for (Passport passport : passports) {
            System.err.println(passport.getName() + " - " + new String(DECODER.decode(passport.getData())));
        }
    }
}
