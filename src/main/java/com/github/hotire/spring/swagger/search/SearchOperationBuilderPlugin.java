package com.github.hotire.spring.swagger.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.schema.CollectionType;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.ParameterContext;

@Slf4j
@Component
public class SearchOperationBuilderPlugin implements OperationBuilderPlugin {

    private final ResolvedType resolvedType;

    public SearchOperationBuilderPlugin(TypeResolver resolver) {
        this.resolvedType = resolver.resolve(Search.class);
    }

    @Override
    public void apply(OperationContext context) {
        final List<ResolvedMethodParameter> methodParameters = context.getParameters();
        final List<RequestParameter> requestParameters = new ArrayList<>();
        int index = 0;
        for (ResolvedMethodParameter methodParameter : methodParameters)
            if (methodParameter.getParameterType().equals(resolvedType)) {
                log.info("hello");
                final ParameterContext parameterContext = new ParameterContext(methodParameter,
                                                                               context.getDocumentationContext(),
                                                                               context.getGenericsNamingStrategy(),
                                                                               context,
                                                                               index++);

                requestParameters.add(parameterContext.requestParameterBuilder()
                                                      .in(ParameterType.QUERY)
                                                      .query(q -> q.model(m -> m.collectionModel(cb -> cb.collectionType(CollectionType.ARRAY)
                                                                                                         .model(mb -> mb.scalarModel(ScalarType.STRING)))))
                                                      .description("Search criteria in the format: key,value")
                                                      .name("search")
                                                      .build());
            }

        context.operationBuilder().requestParameters(requestParameters);
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
