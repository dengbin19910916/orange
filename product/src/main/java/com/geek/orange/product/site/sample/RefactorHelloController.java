package com.geek.orange.product.site.sample;

import com.geek.orange.api.dto.User;
import com.geek.orange.api.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Hello Controller", description = "Hello handler", basePath = "/")
@RestController
public class RefactorHelloController implements HelloService {

    @ApiOperation("show name")
    @Override
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    @ApiOperation("show name and age")
    @Override
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        return new User(name, age);
    }

    @ApiOperation("show name and age by user")
    @Override
    public String hello(@RequestBody User user) {
        return "Hello " + user.getName() + ", " + user.getAge();
    }
}
