package com.geek.orange.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("swagger")
@PropertySource(value = "classpath:config/messages.properties", encoding = "UTF-8")
public class SwaggerProperty {
}
