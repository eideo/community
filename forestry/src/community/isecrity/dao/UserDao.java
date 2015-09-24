package community.isecrity.dao;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
/**
 * @CopyRight：http://www.netrust.cn/
 *
 * @Description:实现用户模块部分的功能管理dao层   
 * @Author: lazite 
 * @CreateTime: 2015年7月10日 上午9:19:11   
 * @ModifyBy: lazite 
 * @ModeifyTime: 2015年7月10日 上午9:19:11   
 * @ModifyDescription:
 * @Version:   V1.0
 */
public interface UserDao {
	/**
	 * @Title: addUser
	 * @Description: 添加User对象,返回添加对象的id
	 * @param user
	 * @return
	 * @throws: TODO
	 */
	public int addUser(User user);
	
	/**
	 * @Title: addUserRole
	 * @Description: 保存用户与角色之间的关联
	 * @param UserRoleLink
	 * @return
	 * @throws: TODO
	 */
	public int addUserRole(UserRoleLink userRoleLink);
	
	/**
	 * @Title: addUsersRoles
	 * @Description: 批量保存用户与角色的关联关系
	 * @param list
	 * @return
	 * @throws: TODO
	 */
	public int addUsersRolesBatch(List<UserRoleLink> list);
	
	/**
	 * @Title: delUserById
	 * @Description: 根据id删除该用户
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public int delUserById(Integer id);
	
	/** 
	 * @Title: delUserRoles
	 * @Description: 根据user对象的id批量删除该user与角色之间的关联关系
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public int delUserRoles(Integer id);
	
	/**
	 * @Title: delUserRoles
	 * @Description: 根据user对象的id和role对象id 删除所有
	 * @param paramsMap map中的第一个参数为user对象的id.第二个参数为role对象id的数组
	 * @return
	 * @throws: TODO
	 */
	public int delUserRolesBatch(Map paramMap);
	
	/**
	 * @Title: updateUser
	 * @Description: 修改用户对象的信息
	 * @param user
	 * @return
	 * @throws: TODO
	 */
	public int updateUser(User user);
	
	/**
	 * @Title: selectUserById
	 * @Description: 根据id查找User对象,单对象
	 * @param uId
	 * @return
	 * @throws: TODO
	 */
	public User selectUserById(Integer uId);
	
	/**
	 * @Title: selectUserById
	 * @Description: 根据id查找User对象,单对象
	 * @param uId
	 * @return
	 * @throws: TODO
	 */
	public User selectUserAllById(Integer uId);
	
	/**
	 * @Title: selectUserRoleById
	 * @Description: 根据id查找User对象,包含Role集合
	 * @param uId
	 * @return
	 * @throws: TODO
	 */
	public User selectUserRoleById(Integer uId);
	
	/**
	 * 
	 * @Title: selectUserByAccount
	 * @Description: 根据账号密码查询user
	 * @param uId
	 * @return
	 * @throws: TODO
	 */
	public User selectUserByAccount(User user);
	
	/**
	 * @Title: selectUsersByConditon4Pages
	 * @Description:分页条件查询user集合.
	 * @return
	 * @throws: TODO
	 */
    public Page<User> selectUsersByConditon4Pages(Page<User> page,User user);
    
    /**
     * @Title: selectUserCountsByCondtion
     * @Description: 条件查询user的数量 .传参暂时未定.为分页count而设,暂可不实现
     * @return
     * @throws: TODO
     */
    public int selectUserCountsByCondtion();
    /**
     * 根据用户id查询获得用户角色中间表的集合
     * @param uId
     * @return
     */
    public List<UserRoleLink> selectUserRoleLinksById(Integer Id);
	
}