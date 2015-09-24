package community.isecrity.dao;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.hadoop.secrity.entity.Role;
import com.hadoop.secrity.entity.RolePrivilegeLink;
/**
 * @CopyRight：http://www.netrust.cn/
 *
 * @Description::实现角色模块部分的功能管理dao层   
 * @Author: lazite 
 * @CreateTime: 2015年7月10日 上午9:47:17   
 * @ModifyBy: lazite 
 * @ModeifyTime: 2015年7月10日 上午9:47:17   
 * @ModifyDescription:
 * @Version:   V1.0
 */
public interface RoleDao {
	/**
	 * @Title: addRole
	 * @Description: 添加一个角色信息
	 * @param role
	 * @return
	 * @throws: TODO
	 */
	public int addRole(Role role);
	
	/**
	 * @Title: addRolePrivRelation
	 * @Description: 插入权限与角色的关系,建立关联
	 * @param rolePrivilegeLink
	 * @return
	 * @throws: TODO
	 */
	public int addRolePrivRelation(RolePrivilegeLink rolePrivilegeLink);
	
	/**
	 * @Title: addRolePrivsRelation
	 * @Description: 批量插入权限与角色的关联关系
	 * @param roleId 角色id
	 * @param privIds 权限id的集合
	 * @return 操作影响记录条数
	 * @throws: TODO
	 */
	public int addRolePrivsRelation(List<RolePrivilegeLink> list);
	
	
	
	/**
	 * @Title: delRoleById
	 * @Description: 根据id删除一个角色信息
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public int delRoleById(int id);
	
	/**
	 * @Title: delRolePrivsBatch
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 * @throws: TODO
	 */
	@SuppressWarnings("rawtypes")
	public int delRolePrivsBatch(Map paramMap);
	
	
	/** 
	 * @Title: delUserRoles
	 * @Description: 根据role对象的id批量删除该role与权限之间的关联关系
	 * @param id role的id
	 * @return
	 * @throws: TODO
	 */
	public int delRolePrivsByRoleId(int id);
	
	/** 
	 * @Title: delUserRoles
	 * @Description: 根据role对象的id批量删除该role与权限之间的关联关系以及role对象数据.在一个map中写.多
	 * 				多语句一次提交执行.hibernate中可使用级联删除
	 * @param id role的id
	 * @return
	 * @throws: TODO
	 */
	// wl do
	public int delRolePrivsOneStep(int id);
	
	/**
	 * @Title: updateUser
	 * @Description: 修改角色对象的基本信息.不含关联操作
	 * @param user
	 * @return
	 * @throws: TODO
	 */
	public int updateRole(Role role);
	
	/**
	 * @Title: selectRoleById
	 * @Description: 根据id查找一个role对象
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public Role selectRoleById(Integer id);
	
	
	
	/**
	 * @Title: selectRoleList
	 * @Description: 选择所有的角色集合
	 * @return List<Role>
	 * @throws: TODO
	 */
	public List<Role> selectRoleList();
	
	
	
	/**
	 * @Title: selectRolePrivsById
	 * @Description: 选择角色对象,包含角色对应权限集合
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public Role selectRolePrivsById(Integer id);
	
	
	/**
	 * @Title: selectRolesByCondtion
	 * @Description: 选择角色对象集合
	 * @param page//此参数在没有分页的情况下已失去意义
	 * @return
	 * @throws: TODO
	 */
	public Page<Role> selectRolesByCondtion (Page<Role> page,Role role);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public Page<Role> selectRoles(Page<Role> page);
	
}