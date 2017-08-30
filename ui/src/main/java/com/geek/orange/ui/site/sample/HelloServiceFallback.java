package com.geek.orange.ui.site.sample;

import com.geek.orange.api.dto.User;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback {
    public String hello(String name) {
        return "error";
    }

    public User hello(String name, Integer age) {
        return new User("未知", 0);
    }

    public String hello(User user) {
        return "error";
    }
}
