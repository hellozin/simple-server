package me.hellozin.simple.echo;

import java.util.HashSet;
import java.util.Set;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
public class EchoController implements ApplicationListener<ContextRefreshedEvent> {

    private Set<String> handlerMethodPatterns = new HashSet<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        contextRefreshedEvent.getApplicationContext()
                .getBean(RequestMappingHandlerMapping.class)
                .getHandlerMethods()
                .forEach((info, method) -> handlerMethodPatterns.add(info.getPatternValues().iterator().next()));
    }

    @GetMapping("/")
    public String root(@RequestParam(value = "param", required = false) String param) {
        return param != null
                ? param
                : handlerMethodPatterns.toString();
    }

}
