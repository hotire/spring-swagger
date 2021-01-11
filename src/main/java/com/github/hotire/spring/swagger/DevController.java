package com.github.hotire.spring.swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dev")
@RestController
public class DevController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
