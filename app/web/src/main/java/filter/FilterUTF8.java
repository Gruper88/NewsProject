package filter;

import java.io.IOException;
import javax.servlet.*;

public class FilterUTF8 implements Filter {
    public FilterUTF8() {
    }

    public void init(FilterConfig filterconfig)
            throws ServletException {
        filterConfig = filterconfig;
        encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
            throws IOException, ServletException {
        servletrequest.setCharacterEncoding(encoding);
        filterchain.doFilter(servletrequest, servletresponse);
    }

    public void destroy() {
    }

    private String encoding;
    private FilterConfig filterConfig;
}