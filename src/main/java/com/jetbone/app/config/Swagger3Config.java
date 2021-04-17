package com.jetbone.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Chris
 * @date 2021-03-20
 */
@Configuration
@EnableOpenApi
public class Swagger3Config {

    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("API测试")
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jetbone.app.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket loginApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("登陆API")
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jetbone.app.security.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Learn Java")
                .description("Demo Project for Learn Java")
                .contact(new Contact("Chris", "www.nothing.nope", "email"))
                .version("0.0.1")
                .build();
    }

}
