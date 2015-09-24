package community.isecrity.Interceptor;

/**
 * @author wl
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class JspLoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
    
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        /**
         * 如果处理HTTP请求，并且需要访问诸如getHeader或getCookies等在ServletRequest中
         * 无法得到的方法，就要把此request对象构造成HttpServletRequest
         */
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String currentURL = request.getRequestURI(); // 取得根目录所对应的绝对路径:
        HttpSession session = request.getSession(false);
        
 //     &&currentURL.indexOf("top.jsp")==-1&&currentURL.indexOf("left.jsp")==-1
        //如果jsp就验证（login.jsp,及上下左右头部除外）
	    if (currentURL.indexOf("control.jsp")==-1&&currentURL.indexOf(".jsp")>-1) { 
	         // 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
	        if(session == null || session.getAttribute("thisLoginUser") == null ){
	            response.sendRedirect(request.getContextPath()+"/jsp/login.html");
	            return ;
	        }
	        
    	}
	    // 加入filter链继续向下执行
        filterChain.doFilter(request, response);
    }
    public void destroy() {
    	
    }
}
