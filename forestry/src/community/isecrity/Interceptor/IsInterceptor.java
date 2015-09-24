package community.isecrity.Interceptor;

import java.awt.print.Printable;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hadoop.util.RegExUtil;
import com.hadoop.util.enums.MenuEnums;
import com.hadoop.util.enums.MenuEnums.Menu;
import com.hadoop.util.out.Print;

public class IsInterceptor extends HandlerInterceptorAdapter{
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

	@SuppressWarnings({"unchecked" })
	public boolean preHandle(HttpServletRequest request,  
    	HttpServletResponse response, Object handler) throws IOException  {    	
    	//String method=RegExUtil.splitUrlForMethod(request.getRequestURI());	
    	String menu=RegExUtil.splitUrlForMenu(request.getRequestURI());	
    	HttpSession session=request.getSession();
    	String contextPath=request.getContextPath();
    	
    	menu=request.getRequestURI().split("/")[2];
    	String cmethod=request.getRequestURI().split("/")[3];
    	
    	if(!allowUrls.contains(menu)){//判断是否是允许通过的路径
			//获取session存储的权限
			List<String> secrity=(List<String>)session.getAttribute("privs");
			String s=menu+"_"+cmethod;

			Print.out(s);		
    		//System.out.println(secrity.toString());	
    		Print.out(secrity);	
			
			String req=s.split(".do")[0];
			if(secrity!=null){
				Print.out(MenuEnums.Menu.valueOf(Menu.class,menu)
						+"_"
						+MenuEnums.Menu.valueOf(Menu.class,req));	
				
				//判断是否包含栏目权限
				if(secrity.contains(
						MenuEnums.Menu.valueOf(Menu.class,menu)
						+"_"
						+MenuEnums.Menu.valueOf(Menu.class,req))){
					return true;
				}else if(request.getRequestURI().contains("queryQuarterPN")){//进入main页面排除包含	
					return true;
				}else{
					//Print.out("3");
					response.sendRedirect(contextPath+errorPage); // 返回错误页面 
    				return false;
    			}
			}else{
				response.sendRedirect(contextPath+errorPage); // 返回错误页面 
				return false;				
			}
    	}
    	return true;
    }  
  
	
    @SuppressWarnings("unused")
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
    

    @SuppressWarnings("unused")
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
        //System.out.println("postHandle");  
    }  
  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object o, Exception excptn)  
            throws Exception {  
        //System.out.println("afterCompletion");  
    }  
}
