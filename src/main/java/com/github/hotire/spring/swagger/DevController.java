package com.github.hotire.spring.swagger;

import java.time.OffsetDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hotire.spring.swagger.search.Search;
import com.github.hotire.spring.swagger.search.SearchArgument;

@RequestMapping("/dev")
@RestController
public class DevController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/search")
    public String search(@SearchArgument Search search) {
        return search.toString();
    }

    @GetMapping("{id}")
    public String path(@PathVariable String id, Search search) {
        System.out.println(search);
        return search.toString();
    }

    @GetMapping("offset")
    public Map<String, OffsetDateTime> offsetDateTime() {
        return Map.of("", OffsetDateTime.now());
    }

}
