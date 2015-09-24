package community.isecrity.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.hadoop.secrity.entity.Privilege;
import com.hadoop.secrity.entity.Role;

public interface RoleService {
	
	/**
	 * @Title: addRole
	 * @Description: 添加一个角色及建立与权限的关联
	 * @param role
	 * @return
	 * @throws: TODO
	 */
	public int addRole(Role role,Integer[] privs);
	
	
	/**
	 * @Title: delRole
	 * @Description: 删除一个角色包含删除角色与权限的关系
	 * @param roleId
	 * @return
	 * @throws: TODO
	 */
	public boolean delRole(Integer roleId);
	
	/**
	 * @Title: updateRole
	 * @Description: 更新权限角色基本信息包含权限关联关系
	 * @param aprivs
	 * @param dprivs
	 * @param role
	 * @return
	 * @throws: TODO
	 */
	public boolean updateRole(Integer[] aprivs,Integer[] dprivs,Role role);
	
	/**
	 * @Title: getRole
	 * @Description: 根据id查找角色对象
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public Role getRole(Integer id);
	
	/**
	 * @Title: selectAllRole
	 * @Description: 获取所有的角色
	 * @return
	 * @throws: TODO
	 */
	public List<Role> selectAllRole();
	
	public List<Privilege> selectAllPrivileges();
	
	/**
	 * @Title: selectRolesByCondtion
	 * @Description: 条件分页查找角色   角色列表页面无分页不需要
	 * @param role
	 * @param pageNum
	 * @return
	 * @throws: TODO
	 */
	public Page<Role> selectRolesByCondtion(Role role,int pageNum);
	/**
	 * 分页查找角色    角色列表页面无分页不需要
	 * @param pageNum
	 * @return
	 */
	public Page<Role> selectRoles(Integer pageNum);
	/**
	 * 增加角色显示所有的一级菜单和二级菜单
	 * @return
	 */
	public String getAddRoleJson();
}
