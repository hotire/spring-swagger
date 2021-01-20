package com.github.hotire.spring.swagger;

import java.util.Collections;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Search {
    public static final Search EMPTY = new Search();
    private final Set<Entry> entrySet;

    public Search() {
        entrySet = Collections.emptySet();
    }

    @Getter
    @AllArgsConstructor
    public static class Entry {
        public static final String DELIMITER = " ";
        private final String key;
        private final String value;
    }
}
