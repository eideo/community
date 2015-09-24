package community.isecrity.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hadoop.util.RegExUtil;

public class SecrityInterceptor extends HandlerInterceptorAdapter{
	private String errorPage;
	public String getErrorPage() {
		return errorPage;
	}
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	// 重写 preHandle()方法，在业务处理器处理请求之前对该请求进行拦截处理  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {
    	
    	String method=RegExUtil.splitUrlForMethod(request.getRequestURI());	
    	HttpSession session=request.getSession();
    	String contextPath=request.getContextPath();
    	 
    	if(session!=null){
	    	String secrity=(String) request.getSession().getAttribute("secrity");
	    	if(secrity!=null){
		    	if(secrity.contains(getMethodSecirty(method, getMenuSecirty(method)))){
		    		return true;
		    	}else{
		    		response.sendRedirect(contextPath+errorPage); // 返回错误页面 
		    		return false;
		    	}
	    	}else{
	    		response.sendRedirect(contextPath+"/html/login.html"); // 返回登陆页面 
	    		return false;
	    	}
    	}else{
    		response.sendRedirect(contextPath+"/html/login.html"); // 返回登陆页面 
    		return false;
    	}
    }  
  
    private String getMenuSecirty(String method){
    	String s="";
    	if(method.contains("sys")){
    		s+="系统管理";
    	}else if(method.contains("user")||method.contains("User")){
    		s+="用户管理";
    	}else if(method.contains("role")){
    		s+="角色";
    	}else if(method.contains("menu")){
    		s+="栏目";
    	}	
    	return s;
    }
    
    private String getMethodSecirty(String method,String s){
    	if(method.contains("add")){
    		s+="_添加";
    	}else if(method.contains("del")){
    		s+="_删除";
    	}else if(method.contains("update")){
    		s+="_修改";
    	}else if(method.contains("query")){
    		s+="_查询";
    	}else if(method.contains("accPow")){//授权
    		s+="_授权";
    	}else if(method.contains("import")){//导入
    		s+="_导入";
    	}else if(method.contains("export")){//导出
    		s+="_导出";
    	}
    	
    	//System.out.println(s);
    	return s;
    }
    

    
  
    
    public void postHandle(HttpServletRequest request, 
    		HttpServletResponse response, Object o, ModelAndView mav)  
            throws Exception {  
        System.out.println("postHandle");  
    }  
  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object o, Exception excptn)  
            throws Exception {  
        System.out.println("afterCompletion");  
    }  
}
