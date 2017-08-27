package com.geek.orange.ui.site.sample;

import com.geek.orange.api.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    private final RefactorHelloService refactorHelloService;

    @Autowired
    public ConsumerController(RefactorHelloService refactorHelloService) {
        this.refactorHelloService = refactorHelloService;
    }

    @GetMapping("/feign-consumer3")
    public String helloConsumer3() {
        StringBuilder sb = new StringBuilder();
        sb.append(refactorHelloService.hello("MINI")).append("\n");
        sb.append(refactorHelloService.hello("MINI", 20)).append("\n");
        sb.append(refactorHelloService.hello(new User("MINI", 20))).append("\n");
        System.err.println(sb.toString());
        return sb.toString();
    }

    @GetMapping("/hello")
    public String hello() {
        return "db, db";
    }
}
