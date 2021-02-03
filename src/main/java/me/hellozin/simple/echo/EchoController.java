package me.hellozin.simple.echo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    private static final String DEFAULT_MSG = "Hi, there.";

    @GetMapping("/")
    public String root(@RequestParam(value="param", required = false) String param) {
        return param != null
                ? param
                : DEFAULT_MSG;
    }

}
