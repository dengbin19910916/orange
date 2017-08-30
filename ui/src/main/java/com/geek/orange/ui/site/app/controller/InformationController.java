package com.geek.orange.ui.site.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app/information")
public class InformationController {

    @GetMapping
    public String index(@RequestParam(value = "reqId", required = false) Long reqId, Model model) {
        if (reqId != null) {
            System.out.println(reqId);
            model.addAttribute("reqId", reqId);
        }

        return "app/information";
    }
}
