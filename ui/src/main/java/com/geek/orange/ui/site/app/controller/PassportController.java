package com.geek.orange.ui.site.app.controller;

import com.geek.orange.api.app.dto.Passport;
import com.geek.orange.api.app.service.PassportService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

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
    public String save(@RequestParam("passport") MultipartFile[] files) throws IOException {
        List<Passport> passports = new ArrayList<>();
        for (MultipartFile file : files) {
            passports.add(new Passport(file));
        }
        passportService.save(passports.toArray(new Passport[passports.size()]));

        return "redirect:/ui-service/app/information?reqId=600123";
    }
}
