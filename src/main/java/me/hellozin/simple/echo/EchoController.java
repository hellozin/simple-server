package me.hellozin.simple.echo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
public class EchoController implements ApplicationListener<ContextRefreshedEvent> {

    private Set<String> handlerMethodPatterns = new HashSet<>();

    @Value("${version}")
    private String version;

    @GetMapping("/")
    public String root(@RequestParam(value = "param", required = false) String param) throws UnknownHostException {
        return MessageFormat.format("version: {0}, host: {1}", version, InetAddress.getLocalHost().getHostName());
    }

    @GetMapping("/host")
    public String host() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    @GetMapping("/endpoint")
    public Set<String> endpoint() {
        return handlerMethodPatterns;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        contextRefreshedEvent.getApplicationContext()
                .getBean(RequestMappingHandlerMapping.class)
                .getHandlerMethods()
                .forEach((info, method) -> handlerMethodPatterns.add(info.getPatternValues().iterator().next()));
    }

}
