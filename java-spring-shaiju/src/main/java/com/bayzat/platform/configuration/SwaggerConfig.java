package com.bayzat.platform.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType; 
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.bayzat.platform.hr.controllers"))
                .paths(regex("/companies.*"))
                .build().apiInfo(customApiInfo());
    }
    
    private ApiInfo customApiInfo() {
    	  ApiInfo apiInfo = new ApiInfo("Bayzat Rest API","Bayzat HR Platform Rest API","1.0","Terms of service","Shaiju Soman","License Version 1.0","http://localhost:8080/license.html");
        return apiInfo;
    }
}
