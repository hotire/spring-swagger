package com.github.hotire.spring.swagger.search;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.hotire.spring.swagger.search.Search.Entry;

public class SearchHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String DEFAULT_PARAMETER = "search";
    private static final String DEFAULT_DELIMITER = ",";

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
        Set<Entry> entrySet = Arrays.stream(searchParameter)
                                    .map(str -> str.split(DEFAULT_DELIMITER, 2))
                                    .filter(pair -> pair.length == 2 && !pair[1].isEmpty())
                                    .map(this::getEntry)
                                    .collect(Collectors.toSet());
        return new Search(entrySet);
    }

    private Entry getEntry(String[] pair) {
        SearchKey searchKey = SearchKey.valueOf(pair[0]);
        return new Entry(searchKey, pair[1]);
//        return new Entry(pair[0], pair[1]);
    }

}
