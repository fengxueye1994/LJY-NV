package com.lsljy.component;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 配置http请求的相关参数，跨域等
 */
@Component
public class SimpleCORSFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
        response.setHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(request, res);
    }
    public void init(FilterConfig filterConfig) {
    }
    public void destroy() {
    }

}
