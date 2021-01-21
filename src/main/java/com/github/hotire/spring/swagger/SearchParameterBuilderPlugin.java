package com.github.hotire.spring.swagger;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.github.hotire.spring.swagger.search.SearchKey;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.schema.CollectionType;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ExpandedParameterBuilderPlugin;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.spi.service.contexts.ParameterExpansionContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Slf4j
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
@Component
public class SearchParameterBuilderPlugin implements ParameterBuilderPlugin, ExpandedParameterBuilderPlugin {

    private final ResolvedType resolvedType;

    public SearchParameterBuilderPlugin(TypeResolver resolver) {
        this.resolvedType = resolver.resolve(SearchKey.class);
    }

    @Override
    public void apply(ParameterContext parameterContext) {
        final ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
        methodParameter.getParameterType()
                       .getTypeParameters()
                       .stream()
                       .filter(resolvedType::equals)
                       .findAny()
                       .ifPresent(it -> {
                           log.info("{}", it);
                       });
        if (resolvedType.equals(methodParameter.getParameterType())) parameterContext.requestParameterBuilder()
                                                                                     .in(ParameterType.QUERY)
                                                                                     .query(q -> q.model(m -> m.collectionModel(cb -> cb.collectionType(CollectionType.LIST))
                                                                                                               .scalarModel(ScalarType.STRING)))
                                                                                     .description("Search criteria in the format: key,value")
                                                                                     .name("search")
                                                                                     .build();
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    @Override
    public void apply(ParameterExpansionContext context) {
        if (resolvedType.equals(context.getFieldType())) context.getRequestParameterBuilder()
                                                                .in(ParameterType.QUERY)
                                                                .query(q -> q.model(m -> m.collectionModel(cb -> cb.collectionType(CollectionType.LIST))
                                                                                          .scalarModel(ScalarType.STRING)))
                                                                .description("Search criteria in the format: key,value")
                                                                .name("search")
                                                                .build();
    }
}
