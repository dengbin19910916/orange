package com.geek.orange.ui.site.app.controller;

import com.geek.orange.api.app.dto.Passport;
import com.geek.orange.api.app.service.PassportService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Base64;

@Controller
@RequestMapping("/app/passport")
public class PassportController {

    private static final Logger log = LogManager.getLogger(PassportController.class);

    private final PassportService passportService;

    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    @Autowired
    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping
    public String index(@RequestParam(value = "reqId", required = false) Long reqId, Model model) {
        if (reqId != null) {
            Passport[] passports = passportService.search(reqId);
            model.addAttribute("passports", passports);
            System.err.println("passports = " + Arrays.toString(passports));
        }

        return "app/passport";
    }

    @PostMapping
    public String save(@RequestParam("passport") MultipartFile[] passports) {
//        Passport[] passports = new Passport[2];
//        Passport p1 = new Passport("one.jpg", ENCODER.encodeToString("哈士奇".getBytes()));
//        passports[0] = p1;
//        Passport p2 = new Passport("two.jpg", ENCODER.encodeToString("萨摩耶".getBytes()));
//        passports[1] = p2;
//        passportService.save(passports);
        System.err.println("passport length = " + passports.length);
        for (MultipartFile passport : passports) {
            System.out.println("passport name: " + passport.getOriginalFilename());
        }
        return "redirect:/ui-service/app/information?reqId=600123";
    }
}
