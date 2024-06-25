package com.atguigu.cloud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfiguration {

    @Bean
    public GroupedOpenApi orderApi() {      // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("order-api")         // 分组名称
                .pathsToMatch("/order/**")  // 接口请求路径规则
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("order")
                        .description("订单微服务模块接口")
                        .version("v1")
                        .contact(new Contact().name("hexin").email("2077489004@qq.com"))
                        .license(new License().name("Apache 2.0"))
                );
    }
}
