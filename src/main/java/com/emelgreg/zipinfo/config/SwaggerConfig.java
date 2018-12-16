package com.emelgreg.zipinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.emelgreg.zipinfo"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Zip Weather sample code",
                "zip weater demonstrates a hexagonal implementation of a spring boot microservice for an http endpoint with http clients",
                "0.1",
                "https://github.com/gregemel/zipinfo",
                new Contact("greg emel", "https://github.com/gregemel/zipinfo", "greg (dot) emel (at) gmail (dot) com"),
                "mit",
                "https://github.com/gregemel/zipinfo"
        );

        return apiInfo;
    }
}
