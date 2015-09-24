package community.isecrity.service;

import java.util.List;

import com.hadoop.secrity.entity.User;

/**
 * @CopyRight：http://www.netrust.cn/
 *
 * @Description:操作权限相关的控制层.不包括登陆及操作权限验证   
 * @Author: lazite 
 * @CreateTime: 2015年7月10日 下午4:29:22   
 * @ModifyBy: lazite 
 * @ModeifyTime: 2015年7月10日 下午4:29:22   
 * @ModifyDescription:
 * @Version:   V1.0
 */
public interface SecrityService {	
	/**
	 * @Title: getPrivsForUser
	 * @Description: 获取用户的操作权限
	 * @param uId
	 * @return
	 * @throws: TODO
	 */
	public String[] getPrivsForUser(int uId);
	
	/**
	 * @Title: getPrivsMenuForUser
	 * @Description: 获取用户拥有操作权限的栏目
	 * @param uId
	 * @return
	 * @throws: TODO
	 */
	public List<String> getPrivsMenuForUser(int uId);
	
	/**
	 * @Title: addUserRoles
	 * @Description: 为用户赋予权限集合
	 * @param user
	 * @param roleIds
	 * @return true添加成功  false添加失败
	 * @throws: TODO
	 */
	public boolean addUserRoles(User user,String roleIds);
	
	/**
	 * @Title: delUserRoles
	 * @Description: 删除用户的某些权限集合
	 * @param user
	 * @param roleIds
	 * @return
	 * @throws: TODO
	 */
	public boolean delUserRoles(User user,String roleIds);
	
	/**
	 * @Title: updateUserRoles
	 * @Description: 更新用户的角色集合.页面统一提交时请调用该方法
	 * @param user
	 * @param addRoleIds 新增的角色组
	 * @param delRoleIds 删除的角色组
	 * @return true为更新成功  false为更新失败
	 * @throws: TODO
	 */
	public boolean updateUserRoles(User user,String addRoleIds,String delRoleIds);
	
	
}
