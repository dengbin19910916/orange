package com.geek.orange.product.site.sample;

import com.geek.orange.api.dto.User;
import com.geek.orange.api.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Sample Demo控制器", description = "测试用")
@RestController
public class RefactorHelloController implements HelloService {

    @ApiOperation(value = "显示名称")
    @Override
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    @ApiOperation("显示名称和姓名")
    @Override
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        return new User(name, age);
    }

    @ApiOperation("使用User对象显示名称和姓名")
    @Override
    public String hello(@RequestBody User user) {
        return "Hello " + user.getName() + ", " + user.getAge();
    }
}
