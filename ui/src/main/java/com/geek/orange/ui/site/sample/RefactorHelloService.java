package com.geek.orange.ui.site.sample;

import com.geek.orange.api.service.HelloService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "PRODUCT-SERVICE")
public interface RefactorHelloService extends HelloService {
}
