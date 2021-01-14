package com.github.hotire.spring.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).globalRequestParameters(List.of(requestParameter()))
                                                      .select().apis(RequestHandlerSelectors.any())
                                                      .paths(PathSelectors.ant("/v1/**").or(PathSelectors.ant("/dev/**")))
                                                      .build();

    }

    public RequestParameter requestParameter() {
        return new RequestParameterBuilder().name("token")
                                            .in(ParameterType.HEADER)
                                            .description("token")
                                            .required(false)
                                            .build();
    }
}
