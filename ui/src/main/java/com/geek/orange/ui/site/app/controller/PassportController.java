package com.geek.orange.ui.site.app.controller;

import com.geek.orange.api.app.dto.Passport;
import com.geek.orange.api.app.service.PassportService;
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
import java.util.List;

@Controller
@RequestMapping("/app/passport")
public class PassportController {

    private final PassportService passportService;

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
        String[] paths = passportService.save(passports.toArray(new Passport[passports.size()]));
        System.err.println("paths = " + Arrays.toString(paths));

        return "redirect:/ui-service/app/information?reqId=600123";
    }
}
