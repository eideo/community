package community.isecrity.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.hadoop.secrity.dao.MenuDao;
import com.hadoop.secrity.dao.RoleDao;
import com.hadoop.secrity.dao.UserDao;
import com.hadoop.secrity.entity.Menu;
import com.hadoop.secrity.entity.Privilege;
import com.hadoop.secrity.entity.Role;
import com.hadoop.secrity.entity.User;
import com.hadoop.secrity.entity.UserRoleLink;
import com.hadoop.secrity.service.SecrityService;
import com.hadoop.secrity.service.UserService;
import com.hadoop.util.BaseConstant;
import com.hadoop.util.CommonUtils;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private SecrityService secrityService;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Page<User> selectUsersByConditon4Pages(User user,int pageNum){
		Page<User> page=new Page<User>();
		page.setPageNum(pageNum);
		page.setPageSize(BaseConstant.PAGESIZE);
		return userDao.selectUsersByConditon4Pages(page, user);
	}
	
	@Override
	public User selectUserRoleById(Integer id) {
		return userDao.selectUserRoleById(id);
	}

	@Override
	public List<UserRoleLink> selectUserRoleLinksById(Integer Id) {
		return userDao.selectUserRoleLinksById(Id);
	}

	@Override
	public boolean addUser(User user, String roleIds) {
		if(user != null && !"".equals(user)){
			Integer result = userDao.addUser(user);//插入用户表
			if(result != null && !"".equals(result) && result != 0){
				User newUser = new User();
				newUser.setId(result);
				boolean log = secrityService.addUserRoles(newUser, roleIds);//插入用户角色表
				if(log){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean delUser(User user, String roleIds) {
		if(user != null && !"".equals(user)){
			boolean log = secrityService.delUserRoles(user, roleIds);//删除用户角色表
			if(log){
				Integer result = userDao.delUserById(user.getId());//删除用户表
				if(result != null && !"".equals(result) && result != 0){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean updateUser(User user, String addRoleIds, String delRoleIds) {
		if(user != null && !"".equals(user)){
			Integer result = userDao.updateUser(user);//修改用户表
			if(result != null && !"".equals(result) && result != 0){
				boolean log = secrityService.updateUserRoles(user, addRoleIds, delRoleIds);//修改用户角色表
				if(log){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public User selectUser(User user) {
		return userDao.selectUserByAccount(user);
	}
	
	@Override
	public Map<Menu,List<Menu>> selectAll4Menus(User user){
		Map<Menu,List<Menu>> map=new LinkedHashMap<Menu,List<Menu>>();
		List<Integer> list=new ArrayList<Integer>();
		
		User iuser=userDao.selectUserRoleById(user.getId());
		
		if(iuser!=null){
			List<Role> roles=iuser.getList();
			for (Role role : roles) {
				List<Privilege> privileges=roleDao.selectRolePrivsById(role.getId()).getPlist();
				for (Privilege privilege : privileges) {
					list.add(privilege.getMid());//获取所有拥有的栏目权限
				}
			}
		}
		
		list=CommonUtils.removeDuplicate(list);
		
		if(list!=null&&list.size()!=0){
			List<Menu> pList=menuDao.selectAllPChildMenu(list);			
			for (Menu menu : pList) {
				map.put(menu,menu.getClist());
			}
		}
		
		return map;
	}
	
}
