package community.isecrity.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.hadoop.secrity.entity.Menu;
import com.hadoop.secrity.entity.User;
import com.hadoop.secrity.entity.UserRoleLink;

public interface UserService {
	
	public Page<User> selectUsersByConditon4Pages(User user,int pageNum);
	
	public User selectUserRoleById(Integer id);
	
	public List<UserRoleLink> selectUserRoleLinksById(Integer uId);
	/**
	 * 增加用户，至少选择一个角色，先插入用户，再插入用户角色表
	 * @param user
	 * @param roleIds
	 * @return
	 */
	public boolean addUser(User user,String roleIds);
	/**
	 * 删除用户，先删除用户角色表，再删除用户表
	 * @param user
	 * @param roleIds
	 * @return
	 */
	public boolean delUser(User user,String roleIds);
	/**
	 * 修改用户，先修改用户表，再修改用户角色表
	 * @param user
	 * @param addRoleIds
	 * @param delRoleIds
	 * @return
	 */
	public boolean updateUser(User user,String addRoleIds,String delRoleIds);
	
	/**
	 * 
	 * @Title: selectUser
	 * @Description: 根据用户名和密码检查用户
	 * @param user
	 * @return
	 * @throws: TODO
	 */
	public User selectUser(User user);
	
	/**
	 * @Title: selectAllMenus
	 * @Description: 查找显示栏目
	 * @return
	 * @throws: TODO
	 */
	public Map<Menu,List<Menu>> selectAll4Menus(User user);
}
