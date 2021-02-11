package me.hellozin.simple.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${name}")
    private String name;

    @Value("${age}")
    private int age;

    @GetMapping("/name")
    public String name() {
        return name;
    }

    @GetMapping("/age")
    public int age() {
        return age;
    }

}
