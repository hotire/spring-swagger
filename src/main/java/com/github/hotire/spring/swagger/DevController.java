package com.github.hotire.spring.swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hotire.spring.swagger.search.Search;

@RequestMapping("/dev")
@RestController
public class DevController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/search")
    public String search(Search search) {
        return search.toString();
    }

    @GetMapping("{id}")
    public String path(@PathVariable String id, Search search) {
        return id;
    }

}
