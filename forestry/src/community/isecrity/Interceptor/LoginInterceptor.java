package community.isecrity.Interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	private String errorPage;
	private List<String> allowUrls;
	
	public List<String> getAllowUrls() {
		return allowUrls;
	}
	public void setAllowUrls(List<String> allowUrls) {
		this.allowUrls = allowUrls;
	}
	
	public String getErrorPage() {
		return errorPage;
	}
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

    public boolean preHandle(HttpServletRequest request,  
    	HttpServletResponse response, Object handler) throws IOException  {
    	HttpSession session=request.getSession();
    	String contextPath=request.getContextPath();
    	String cmenu=request.getRequestURI().split("/")[2];
    	
    	if(!allowUrls.contains(cmenu)){
	    	if(session.getAttribute("thisLoginUser")==null){
	    		response.sendRedirect(contextPath+errorPage); // 返回错误页面 
				return false;
	    	}
    	}
    	return true;
    }  
  
    public void postHandle(HttpServletRequest request, 
    		HttpServletResponse response, Object o, ModelAndView mav)  
            throws Exception {  
    }  
  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object o, Exception excptn)  
            throws Exception {  
    }  
}
