package com.github.hotire.spring.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hotire.spring.swagger.search.Search;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).globalRequestParameters(List.of(requestParameter(), requestParameter2(), requestParameter3()))
                                                      .ignoredParameterTypes(Search.class)
                                                      .select()
                                                      .apis(RequestHandlerSelectors.any())
                                                      .paths(PathSelectors.ant("/v1/**").or(PathSelectors.ant("/dev/**")))
                                                      .build();

    }

    public RequestParameter requestParameter() {
        return new RequestParameterBuilder().name("3token")
                                            .in(ParameterType.HEADER)
                                            .precedence(1)
                                            .description("token")
                                            .required(false)
                                            .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))
                                                         .defaultValue("hello"))
                                            .parameterIndex(1)
                                            .build();
    }

    public RequestParameter requestParameter2() {
        return new RequestParameterBuilder().name("token")
                                            .in(ParameterType.HEADER)
                                            .description("2token")
                                            .required(false)
                                            .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))
                                                         .defaultValue("hello"))
                                            .parameterIndex(-4)
                                            .build();
    }

    public RequestParameter requestParameter3() {
        return new RequestParameterBuilder().name("1token")
                                            .in(ParameterType.HEADER)
                                            .description("token")
                                            .required(false)
                                            .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))
                                                         .defaultValue("hello"))
                                            .parameterIndex(2)
                                            .build();
    }

    @Deprecated
    public Parameter parameter() {
        return new ParameterBuilder().name("authorization")
                                     .parameterType("header")
                                     .modelRef(new ModelRef("string"))
                                     .defaultValue("hello")
                                     .description("jwt token")
                                     .required(false)
                                     .build();
    }
}
