package community.isecrity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.hadoop.secrity.entity.Privilege;
import com.hadoop.secrity.entity.Role;
import com.hadoop.secrity.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/queryRole.do")
	public String selectRolesByCondtion(HttpServletRequest request,Model model){	  
		String result = request.getParameter("result");
		if(result == null){
			result = "4";
		}
		List<Role> roles = roleService.selectAllRole();
		model.addAttribute("roles",roles); 
		model.addAttribute("result", result);
		return "system/authority";
	}
	@RequestMapping("/addRole.do")
	public String addRole(HttpServletRequest request,Role role) {
		role.setAddtime(new Date(System.currentTimeMillis()));
		String[] privilegeIdsArray = request.getParameterValues("newPrivs");
		Integer[] privilegeIds = new Integer[privilegeIdsArray.length]; 
		for(int i=0;i<privilegeIdsArray.length;i++){
			privilegeIds[i] = Integer.parseInt(privilegeIdsArray[i]);
		}
		int result = roleService.addRole(role, privilegeIds);
		if(result != 0){
			//增加角色成功
			return "redirect:/role/queryRole.do?result=null";
		}
		//增加角色失败
		return "redirect:/role/queryRole.do?result=1";
	}
	@RequestMapping("/delRole.do")
	public String delRole(HttpServletRequest request){	
		Integer roleId = Integer.parseInt(request.getParameter("roleId"));
		if(roleId != null && !"".equals(roleId)){
			boolean flag = roleService.delRole(roleId);
			if(flag){
				//删除成功
				return "redirect:/role/queryRole.do?result=null";
			}
		}
		//删除失败
		return "redirect:/role/queryRole.do?result=2";
	}
	@RequestMapping("/updateRole.do")
	public String updateRole(HttpServletRequest request,Role role,HttpServletResponse response) throws Exception{	
		role.setEditime(new Date(System.currentTimeMillis()));
		String[] aprivsArray = request.getParameterValues("newPrivs");
		String dprivsStr = request.getParameter("oldPrivs");
		if(dprivsStr == null || "".equals(dprivsStr)){
			dprivsStr = "0";
		}
		String[] dprivsArray = dprivsStr.split(",");
		Integer[] aprivs = new Integer[aprivsArray.length];
		Integer[] dprivs = new Integer[dprivsArray.length];
		for(int i=0;i<aprivsArray.length;i++){
			aprivs[i] = Integer.parseInt(aprivsArray[i]);
		}
		for(int i=0;i<dprivsArray.length;i++){
			dprivs[i] = Integer.parseInt(dprivsArray[i]);
		}
		boolean flag = roleService.updateRole(aprivs, dprivs, role);
		if(flag){
			//更新成功
			return "redirect:/role/queryRole.do?result=null";
		}
		//更新失败
		return "redirect:/role/queryRole.do?result=3";
	}
	@RequestMapping("/selectAllPrivileges.do")
	public void selectAllPrivileges(HttpServletResponse response) throws IOException{
		List<Privilege> priList = roleService.selectAllPrivileges();
		StringBuilder priviBuilder = new StringBuilder();
//		拼接最初的中括号,内部存入个模块的集合(包括一级模块和二级模块)
		priviBuilder.append("[");
		for (Privilege privilege : priList) {
			if(privilege.getDesc()==2){

				//拼接其中一个模块
				//拼接一级的内容
				priviBuilder.append("{\"menu\":");
				priviBuilder.append("\""+privilege.getName()+"\",");
				priviBuilder.append("\"menuId\":");
				priviBuilder.append(privilege.getId()+",");
				int pMenuId = privilege.getMid();
				//拼接二级的内容
				priviBuilder.append("\"menus\":[");
				for (Privilege privilege2 : priList) {
					if(privilege2.getDesc()==pMenuId){
						priviBuilder.append("\""+privilege2.getName()+"\",");
					}
				}
				priviBuilder.setLength(priviBuilder.length()-1);
				priviBuilder.append("],");
				
				//拼接二级的id
				priviBuilder.append("\"menuIds\":[");
				for (Privilege privilege2 : priList) {
					if(privilege2.getDesc()==pMenuId){
						priviBuilder.append(privilege2.getId()+",");
					}
				}
				priviBuilder.setLength(priviBuilder.length()-1);
				priviBuilder.append("]");
				priviBuilder.append("},");
			}
		}
		priviBuilder.setLength(priviBuilder.length()-1);
		priviBuilder.append("]");
		String json = "{\"ps\":" + priviBuilder.toString() + "}";
		System.out.println(json);
		response.setContentType("text/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(json);
	}
	@RequestMapping("/queryRoleById.do")
	public void selectRoleById(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer roleId = Integer.parseInt(request.getParameter("roleId"));
		/**
		 * 查询被选中的权限
		 */
		Role role = roleService.getRole(roleId);
		String json = JSON.toJSONString(role);
		List<String> list = new ArrayList<String>();
		
		/**
		 * 查询所有的权限
		 */
		List<Privilege> priList = roleService.selectAllPrivileges();
		StringBuilder priviBuilder = new StringBuilder();
//		拼接最初的中括号,内部存入个模块的集合(包括一级模块和二级模块)
		priviBuilder.append("[");
		for (Privilege privilege : priList) {
			if(privilege.getDesc()==2){

				//拼接其中一个模块
				//拼接一级的内容
				priviBuilder.append("{\"menu\":");
				priviBuilder.append("\""+privilege.getName()+"\",");
				priviBuilder.append("\"menuId\":");
				priviBuilder.append(privilege.getId()+",");
				int pMenuId = privilege.getMid();
				//拼接二级的内容
				priviBuilder.append("\"menus\":[");
				for (Privilege privilege2 : priList) {
					if(privilege2.getDesc()==pMenuId){
						priviBuilder.append("\""+privilege2.getName()+"\",");
					}
				}
				priviBuilder.setLength(priviBuilder.length()-1);
				priviBuilder.append("],");
				
				//拼接二级的id
				priviBuilder.append("\"menuIds\":[");
				for (Privilege privilege2 : priList) {
					if(privilege2.getDesc()==pMenuId){
						priviBuilder.append(privilege2.getId()+",");
					}
				}
				priviBuilder.setLength(priviBuilder.length()-1);
				priviBuilder.append("]");
				priviBuilder.append("},");
			}
		}
		priviBuilder.setLength(priviBuilder.length()-1);
		priviBuilder.append("]");
		json = json.substring(0, json.length()-1)+",\"ps\":"+priviBuilder.toString()+"}";
		System.out.println(json);
		response.setContentType("text/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(json);
	}
}