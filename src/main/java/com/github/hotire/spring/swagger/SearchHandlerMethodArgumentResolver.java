package com.github.hotire.spring.swagger;

import java.util.Optional;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class SearchHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver  {
    private static final String DEFAULT_PARAMETER = "search";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Search.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String[] searchParameter = webRequest.getParameterValues(DEFAULT_PARAMETER);
        return Optional.ofNullable(searchParameter)
                       .map(this::parseSearch)
                       .orElse(Search.EMPTY);
    }

    private Search parseSearch(String[] searchParameter) {
        return new Search();
    }

}
