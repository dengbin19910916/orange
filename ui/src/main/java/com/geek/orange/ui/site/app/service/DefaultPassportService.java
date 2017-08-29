package com.geek.orange.ui.site.app.service;

import com.geek.orange.api.app.service.PassportService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "PRODUCT-SERVICE")
public interface DefaultPassportService extends PassportService {
}
