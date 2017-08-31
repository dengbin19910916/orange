package com.geek.orange.api.app.service;

import com.geek.orange.api.app.dto.Passport;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/app")
public interface PassportService {

    @GetMapping("/passport")
    Passport[] search(@RequestParam("reqId") Long reqId);

    @PostMapping("/passport")
    String[] save(@RequestBody Passport[] passports);
}
