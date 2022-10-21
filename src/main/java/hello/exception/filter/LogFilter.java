package hello.exception.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LogFilter.init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest HttpRequest = (HttpServletRequest) request;
        String requestURI = HttpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        try {
            log.info("REQUEST [{}][{}][{}]", uuid, requestURI, request.getDispatcherType());
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }finally {
            log.info("RESPONSE [{}][{}][{}]", uuid, requestURI, request.getDispatcherType());
        }
    }

    @Override
    public void destroy() {
        log.info("LogFilter.destroy");
    }
}
