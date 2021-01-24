package com.github.hotire.spring.swagger;

import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.swagger.models.Swagger;
import io.swagger.models.parameters.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.web.SwaggerTransformationContext;
import springfox.documentation.swagger2.web.WebMvcSwaggerTransformationFilter;

/**
 * Change the order of requestParameters
 *
 */
@Component
public class CustomWebMvcSwaggerTransformationFilter implements WebMvcSwaggerTransformationFilter {
    @Override
    public Swagger transform(SwaggerTransformationContext<HttpServletRequest> context) {
        context.getSpecification()
               .getPaths()
               .forEach((__, path) -> path.getOperations().forEach(it -> it.getParameters().sort(Comparator.comparing(Parameter::getIn))));
        return context.getSpecification();
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
