import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "*", filterName = "SecurityFilter")//Birinci bura gelmesinin sebebi urlPatterns = * dur. Yeni her seyfeni gelib burada yoxlayir.
public class SecurityFilter implements Filter {

    public void init(FilterConfig arg0) {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String url = httpServletRequest.getRequestURL().toString();
        Object loggedInObj = httpServletRequest.getSession().getAttribute("loggedIn");
        boolean loggedIn = loggedInObj != null && (Boolean) loggedInObj;

        if (loggedIn || url.contains("js") || url.contains("css") || url.contains("login")) {
            chain.doFilter(req, resp);
            return;
        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.sendRedirect("/webApp_war_exploded/login.jsp");
    }

    public void destroy() {
    }
}