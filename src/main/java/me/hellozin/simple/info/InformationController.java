package me.hellozin.simple.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformationController {

    @Value("${version}")
    private String version;

    @Value("${name}")
    private String name;

    @Value("${age}")
    private int age;

    @GetMapping("/version")
    public String version() {
        return version;
    }

    @GetMapping("/name")
    public String name() {
        return name;
    }

    @GetMapping("/age")
    public int age() {
        return age;
    }

}
