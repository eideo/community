package community.isecrity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.hadoop.secrity.dao.PrivilegeDao;
import com.hadoop.secrity.dao.RoleDao;
import com.hadoop.secrity.entity.Privilege;
import com.hadoop.secrity.entity.Role;
import com.hadoop.secrity.entity.RolePrivilegeLink;
import com.hadoop.secrity.service.RoleService;
import com.hadoop.util.BaseConstant;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDao roleDao;
	@Autowired
	PrivilegeDao privilegeDao;
	
	@Override
	public int addRole(Role role,Integer[] privs) {
		Integer rId = roleDao.addRole(role);
		List<RolePrivilegeLink> list = new ArrayList<RolePrivilegeLink>();
		if(rId!=1){
			for (Integer s : privs) {
				RolePrivilegeLink rpl=new RolePrivilegeLink(null, 
													new Role(rId,null,null,null,null),
													new Privilege(s, null, null, null, null));
				list.add(rpl);
			}
			roleDao.addRolePrivsRelation(list);
		}
		return rId;
	}
	
	@Override
	public boolean delRole(Integer roleId){
		int result = roleDao.delRolePrivsOneStep(roleId);
		if(result != 0){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public boolean updateRole(Integer[] aprivs,Integer[] dprivs,Role role){
		roleDao.updateRole(role);
		
		//判断是否需要删除部分权限
		if(dprivs.length!=0){
			Map paramMap1=new HashMap();
			paramMap1.put("rId", role.getId());
			paramMap1.put("pIdArray", dprivs);
			
			roleDao.delRolePrivsBatch(paramMap1);
		}
		
		//判断是否需要增加新权限
		if(aprivs.length!=0){
			List<RolePrivilegeLink> list=new ArrayList<RolePrivilegeLink>();
			for (Integer s : aprivs) {
				RolePrivilegeLink rpl=new RolePrivilegeLink(null, 
						new Role(role.getId(),null,null,null,null),
						new Privilege(s, null, null, null, null));
				list.add(rpl);
			}
			roleDao.addRolePrivsRelation(list);
		}
		return true;
	}
	
	
	@Override
	public Role getRole(Integer id) {
//		return roleDao.selectRoleById(id);
		return roleDao.selectRolePrivsById(id);
	}

	@Override
	public List<Role> selectAllRole() {
		return roleDao.selectRoleList();
	}

	@Override
	public Page<Role> selectRolesByCondtion(Role role, int pageNum) {
		Page<Role> page = new Page<Role>();
		page.setPageNum(pageNum);
		page.setPageSize(BaseConstant.PAGESIZE);
		return roleDao.selectRolesByCondtion(page, role);
	}

	@Override
	public Page<Role> selectRoles(Integer pageNum) {
		Page<Role> page = new Page<Role>();
		page.setPageNum(pageNum);
		page.setPageSize(BaseConstant.PAGESIZE);
		return roleDao.selectRoles(page);
	}

	@Override
	public List<Privilege> selectAllPrivileges() {
		return privilegeDao.selectAllPrivileges();
	}

	@Override
	public String getAddRoleJson() {
		List<Privilege> parentMenu = privilegeDao.selectAllMenuPrivileges();
		List<Privilege> subMenu = null;
		String json = "";
		List<String> list = new ArrayList<String>();
		for(int i=0;i<parentMenu.size();i++){
			Privilege menu = parentMenu.get(i);
			Privilege privilege = new Privilege();
			privilege.setDesc(menu.getMid());
			subMenu = privilegeDao.selectAllSubMenuPrivileges(privilege);
			json = "menu\":[" + JSON.toJSONString(menu) + "],\"list\":" + JSON.toJSONString(subMenu);
			System.out.println(json);
			list.add(json);
		}
		//}]"]     ]",
		json = JSON.toJSONString(list).toString()
							.replaceAll("\\\\","");
//							.replace("[\"", "[{\"")
//							.replace("]\"", "")
//							.replace("},\"menu\"", "}]},{\"menu\"");
		return json;
	}
}
