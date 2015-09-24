package community.isecrity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hadoop.secrity.dao.MenuDao;
import com.hadoop.secrity.dao.RoleDao;
import com.hadoop.secrity.dao.UserDao;
import com.hadoop.secrity.entity.Menu;
import com.hadoop.secrity.entity.Privilege;
import com.hadoop.secrity.entity.Role;
import com.hadoop.secrity.entity.User;
import com.hadoop.secrity.entity.UserRoleLink;
import com.hadoop.secrity.service.SecrityService;
import com.hadoop.util.CommonUtils;
import com.hadoop.util.out.Print;

@Service("secrityService")
public class SecrityServiceImpl implements SecrityService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	
	@Override
	public String[] getPrivsForUser(int uId) {
		List<String> list=new ArrayList<String>();
		User user=userDao.selectUserRoleById(uId);
		if(user!=null){
			List<Role> roles=user.getList();
			for (Role role : roles) {
				List<Privilege> privileges=roleDao.selectRolePrivsById(role.getId()).getPlist();
				for (Privilege privilege : privileges) {
					list.add(privilege.getMid()+"_"+privilege.getDesc());
					//list.add(privilege.getName());
				}
			}
			//去重加转化String数组
			return CommonUtils.parseList2Array(CommonUtils.removeDuplicate(list));
		}
		return null;
	}

	public List<String> getPrivsMenuForUser(int uId){
		String[] pArray=getPrivsForUser(uId);
		List<String> list=new ArrayList<String>(); 
		if(pArray!=null){
			for (String s : pArray) {
				Integer tempMId=Integer.parseInt(s.split("_")[0]);
				Menu pmenu=menuDao.selectMenuById(tempMId);
				if(pmenu.getPid()==2){
					list.add(pmenu.getName());
				}else{
					Menu cmenu=menuDao.selectMenuById(pmenu.getPid());
					list.add(cmenu.getName()+"_"+pmenu.getName());
				}
			}
			//Print.out(list);
			return list;
		}
		return null;
	}
	
	//全部操作权限栏目
	public List<String> getPrivsMenuForUserAll(int uId){
		Map<Menu,List<Menu>> map=new LinkedHashMap<Menu,List<Menu>>();
		List<String> mlist=new ArrayList<String>();
		
		List<Menu> fmenus=menuDao.selectChildMenu(2);
		for (Menu menu : fmenus) {
			List<Menu> cmenus=menuDao.selectChildMenu(menu.getId());
			map.put(menu, cmenus);
		}
		
		for (Map.Entry<Menu,List<Menu>> entry : map.entrySet()) {
		   for (Menu menu : entry.getValue()) {
			   mlist.add(entry.getKey().getName()+"_"+menu.getName());
			   //System.out.println(entry.getKey().getName()+"-----"+menu.getName());
		   } 
		}
		return mlist;
	}
	
	@Override
	public boolean addUserRoles(User user,String roleIds) {
		boolean flag=true;
		String[] roleIdArray=roleIds.split(",");
		Role role=null;
		UserRoleLink userRoleLink=null;
		List<UserRoleLink> list=new ArrayList<UserRoleLink>();
		if(1==roleIdArray.length){
			role=new Role(Integer.parseInt(roleIdArray[0]), null, null, null, null);
			userRoleLink=new UserRoleLink(null, user, role);
			if(userDao.addUserRole(userRoleLink)!=1)
				flag=false;
		}else if(1<roleIdArray.length){
			for (String roleId : roleIdArray) {
				role=new Role(Integer.parseInt(roleId), null, null, null, null);
				userRoleLink=new UserRoleLink(null, user, role);
				list.add(userRoleLink);
			}
			if(userDao.addUsersRolesBatch(list)!=roleIdArray.length)
				flag=false;
		}
		
		return flag;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean delUserRoles(User user,String roleIds){
		boolean flag=true;
		if("0".equals(roleIds)){
			return flag;
		}
		String[] rIdArray=roleIds.split(",");
		
		if(rIdArray.length>0){
			Map paramMap=new HashMap();
			//此处map的key值必须确定
			paramMap.put("uId", user.getId());	
			paramMap.put("rIdArray", rIdArray);
			if(userDao.delUserRolesBatch(paramMap)!=rIdArray.length)
				flag=false;
		}
		return flag;
	}

	@Override
	public boolean updateUserRoles(User user, String addRoleIds,String delRoleIds) {
		boolean flag=true;
		//只有新增role时
		if(StringUtils.isNotBlank(addRoleIds)&&!StringUtils.isNotBlank(delRoleIds)){
			flag=this.addUserRoles(user, addRoleIds);
		}else if(!StringUtils.isNotBlank(addRoleIds)&&StringUtils.isNotBlank(delRoleIds)){
			//只有删除role时
			flag=this.delUserRoles(user, delRoleIds);
		}else if(StringUtils.isNotBlank(addRoleIds)&&StringUtils.isNotBlank(delRoleIds)){
			//新增role和删除role都有
			if(!this.delUserRoles(user, delRoleIds)||!this.addUserRoles(user, addRoleIds))
				flag=false;
		}
		return flag;
	}
}
