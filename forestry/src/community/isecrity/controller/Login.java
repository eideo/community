package community.isecrity.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.hadoop.secrity.entity.Menu;
import com.hadoop.secrity.entity.User;
import com.hadoop.secrity.service.SecrityService;
import com.hadoop.secrity.service.UserService;
import com.hadoop.util.out.Print;

@Controller
@RequestMapping("/login/")
public class Login {
	@Autowired
	SecrityService secrityService;
	@Autowired
	UserService userService;
	
	@RequestMapping("in.do")
	public String login(User user,HttpServletRequest request,Model model){
		try{
			Integer userId=null;
			if(user.getId()==null){
				userId=(Integer) request.getSession().getAttribute("thisLoginUser");
			}else{
				userId=user.getId();
			}
			List<String> mprivs=secrityService.getPrivsMenuForUser(userId);
			
			//Print.outList_Line(mprivs);
			
			Map<Menu, List<Menu>> map=userService.selectAll4Menus(user);//拥有权限的栏目
			request.getSession().setAttribute("privs",mprivs);//session存储一份栏目权限
			model.addAttribute("map", map);
			return "main";
		}catch(Exception e){
			return "redirect:/jsp/login.html";
		}
	}
	
	@RequestMapping("alog.do")
	public void ajaxLogin(User user,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		response.setCharacterEncoding("utf-8");
		user=userService.selectUser(user);
		if(user!=null){
			Integer userId=user.getId();
			request.getSession().setAttribute("thisLoginUser",userId);
			request.getSession().setAttribute("thisLoginName",user.getAccount());
		}else{
			PrintWriter out = response.getWriter();			
			out.print("-1");
		}
	}
	
	//****************************************************************
	//退出登录
	//****************************************************************
	@RequestMapping("out.do")
	public String loginOut(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("thisLoginUser");
		session.removeAttribute("thisLoginName");
		session.removeAttribute("privs");
		return "redirect:/jsp/login.html";
	}
}
