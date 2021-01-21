package com.github.hotire.spring.swagger.search;

import java.util.Collections;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Search {
    public static final Search EMPTY = new Search();
    private final Set<Entry> entrySet;

    public Search() {
        entrySet = Collections.emptySet();
    }

    @Data
    @AllArgsConstructor
    public static class Entry {
        public static final String DELIMITER = " ";
        private final SearchKey key;
        private final String value;
    }
}
