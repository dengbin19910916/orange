package com.geek.orange.ui.site.app.service;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "PRODUCT-SERVICE", fallback = PassportServiceFallback.class)
public interface PassportService extends com.geek.orange.api.app.service.PassportService {
}
