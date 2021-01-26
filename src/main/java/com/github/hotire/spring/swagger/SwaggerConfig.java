package com.github.hotire.spring.swagger;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.github.hotire.spring.swagger.search.Search;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public WebMvcConfigurer legacySwaggerPath() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addRedirectViewController("/swagger-ui.html", "/swagger-ui/");
            }
        };
    }

    @Bean
    public Docket api(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalRequestParameters(List.of(requestParameter(), requestParameter2(), requestParameter3()))
                .alternateTypeRules(new AlternateTypeRule(typeResolver.resolve(Map.class, String.class, OffsetDateTime.class), typeResolver.resolve(Map.class, String.class, String.class)))
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
