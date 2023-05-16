// 
// 
// 

package util;

import java.util.Iterator;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;

public class SetChar extends HttpServlet implements Filter
{
    private FilterConfig filterConfig;
    
    public void init(final FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
    
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) {
        try {
            final HttpServletRequest req = (HttpServletRequest)request;
            if (req.getMethod().equalsIgnoreCase("get")) {
                this.encoding(req);
            }
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            filterChain.doFilter(request, response);
        }
        catch (ServletException sx) {
            this.filterConfig.getServletContext().log(sx.getMessage());
        }
        catch (IOException iox) {
            this.filterConfig.getServletContext().log(iox.getMessage());
        }
    }
    
    private void encoding(final HttpServletRequest request) {
        for (final String[] parames : request.getParameterMap().values()) {
            for (int i = 0; i < parames.length; ++i) {
                try {
                    parames[i] = new String(parames[i].getBytes("UTF-8"), "UTF-8");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void destroy() {
    }
}
