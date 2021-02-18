package me.hellozin.simple.interceptor;

import java.net.InetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SimpleInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(SimpleInterceptor.class);

    @Value("${version}")
    private String version;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.error("version [{}]: here is [{}]. your request [{}]", version, InetAddress.getLocalHost().getHostName(), request.getRequestURI());
        return true;
    }
}
