package com.geek.orange.ui.site.app.service;

import com.geek.orange.api.app.dto.Passport;
import org.springframework.stereotype.Component;

@Component
public class PassportServiceFallback {
    public Passport[] search(Long reqId) {
        System.err.println("error 1");
        return new Passport[0];
    }

    public void save(Passport[] passports) {
        System.err.println("error 2");
    }
}
