package community.isecrity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.github.pagehelper.Page;

import community.isecrity.entity.User;
import community.isecrity.service.RoleService;
import community.isecrity.service.SecrityService;
import community.isecrity.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private SecrityService secrityService;
	
	@RequestMapping("/queryUser.do")
	public String selectUsersByConditon4Pages(HttpServletRequest request,Model model){		              
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String account = request.getParameter("accountCond");
		String phone = request.getParameter("phoneCond");
		String result = request.getParameter("result");
		if(result == null){
			result = "4";
		}
		User user = new User();
		user.setAccount(account);
		user.setPhone(phone);
		Page<User> page = userService.selectUsersByConditon4Pages(user,pageNum);
		Integer totalPages = page.getPages();
		if(totalPages == 0){
			totalPages = 1;
		}
		model.addAttribute("users",page.getResult()); 
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("user", user);
		model.addAttribute("result", result);
		return "system/usermanage";
	}
	
	@RequestMapping("/addUser.do")
	public ModelAndView addUser(HttpServletRequest request,User user){
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String account = request.getParameter("accountCond");
		String phone = request.getParameter("phoneCond");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountCond", account);
		map.put("phoneCond", phone);
		map.put("pageNum", pageNum);
		String[] roleIdsArray = request.getParameterValues("newRoleIds");
		String roleIds = StringUtils.join(roleIdsArray, ",");
		boolean flag = userService.addUser(user, roleIds);
		if(flag){
			return new ModelAndView(new RedirectView("queryUser.do"), map);
		}else{
			map.put("result", "1");
			return new ModelAndView(new RedirectView("queryUser.do"), map);
		}
	}
	
	@RequestMapping("/deleteUser.do")
	public ModelAndView deleteUser(HttpServletRequest request){
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer id = Integer.parseInt(request.getParameter("id"));
		String account = request.getParameter("accountCond");
		String phone = request.getParameter("phoneCond");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountCond", account);
		map.put("phoneCond", phone);
		List<UserRoleLink> list = userService.selectUserRoleLinksById(id);
		if(list.size() == 0){
			UserRoleLink userRoleLink = new UserRoleLink();
			User user = new User();
			user.setId(id);
			Role role = new Role();
			role.setId(0);
			userRoleLink.setUser(user);
			userRoleLink.setRole(role);
			list.add(userRoleLink);
		}
		List<String> roleIdsList = new ArrayList<String>();
		for(UserRoleLink userRoleLink : list){
			roleIdsList.add(userRoleLink.getRole().getId().toString());
		}
		String[] roleIdsArray = new String[roleIdsList.size()];
		String roleIds = StringUtils.join(roleIdsList.toArray(roleIdsArray), ",");
		User user = new User();
		user.setId(id);
		boolean flag = userService.delUser(user, roleIds);
		if(flag){
			//删除成功之后绑定页码
			Page<User> page = userService.selectUsersByConditon4Pages(user, pageNum);
			if(pageNum > page.getPages()){
				pageNum = page.getPages();
			}
			map.put("pageNum", pageNum);
			return new ModelAndView(new RedirectView("queryUser.do"), map);
		}else{
			map.put("result", "2");
			map.put("pageNum", pageNum);
			return new ModelAndView(new RedirectView("queryUser.do"), map);
		}
	}
	
	@RequestMapping("/updateUser.do")
	public ModelAndView updateUser(User user,HttpServletRequest request){
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String account = request.getParameter("accountCond");
		String phone = request.getParameter("phoneCond");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountCond", account);
		map.put("phoneCond", phone);
		map.put("pageNum", pageNum);
		String delRoleIds = request.getParameter("oldRoleIds");
		String[] newRoleIdsArray = request.getParameterValues("newRoleIds");
		String addRoleIds = StringUtils.join(newRoleIdsArray, ",");
		boolean flag = userService.updateUser(user, addRoleIds, delRoleIds);
		if(flag){
			return new ModelAndView(new RedirectView("queryUser.do"), map);
		}else{
			map.put("result", "3");
			return new ModelAndView(new RedirectView("queryUser.do"), map);
		}
	}

	@RequestMapping("/queryUserRolesById.do")
	public void selectUserRolesById(User user,HttpServletResponse response,Map<String,Object> map) throws IOException{		              
		user = userService.selectUserRoleById(user.getId());
		String s=JSONArray.toJSONString(user);
		
		List<String> list = new ArrayList<String>();
		for (Role r : roleService.selectAllRole()) {
			list.add(r.getName());
		}
		String ps=JSON.toJSONString(list);
		s=s.substring(0, s.length()-1)+",\"ps\":"+ps+"}";
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(s);
	}
	
	@RequestMapping("/queryAllRoles.do")
	public void selectAllRoles(HttpServletResponse response) throws IOException{
		List<String> names = new ArrayList<String>();
		List<Role> roles = new ArrayList<Role>();
		for (Role r : roleService.selectAllRole()) {
			names.add(r.getName());
			Role role = new Role();
			role.setId(r.getId());
			role.setName(r.getName());
			roles.add(role);
		}
		String ps = JSON.toJSONString(names);
		String list = JSON.toJSONString(roles);
		String json = "{\"list\":"+ list + ",\"ps\":" + ps + "}";
		response.setContentType("text/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(json);
	}
}
