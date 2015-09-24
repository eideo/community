package community.isecrity.dao;

import java.util.List;

import com.hadoop.secrity.entity.Privilege;

/**
 * @CopyRight：http://www.netrust.cn/
 * @Description: 操作权限的dao层 
 * @Author: lazite 
 * @CreateTime: 2015年7月9日 下午2:01:30   
 * @ModifyBy: lazite 
 * @ModeifyTime: 2015年7月9日 下午2:01:30   
 * @ModifyDescription:
 * @Version:   V1.0
 */
public interface PrivilegeDao {
	
	
	/**
	 * @Title: addPrivilege
	 * @Description: 插入权限.此中插入的权限必须有menu的相关信息
	 * @param privilege
	 * @return 返回插入的privilege的id
	 * @throws: 插入失败返回值为0
	 */
	public int addPrivilege(Privilege privilege);
	
	
	/**
	 * @Title: delPrivilegeById
	 * @Description: 根据id删除一个权限信息
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public int delPrivilegeById(Integer id);
	
	/**
	 * @Title: delPrivilegeByMenuId
	 * @Description: 删除一个栏目的权限信息,在删除栏目时需要提取调用该方法
	 * @param mId
	 * @return
	 * @throws: TODO
	 */
	//do
	public int delPrivilegesByMenuId(Integer mId);
	
	
	/**
	 * @Title: updatePrivilege
	 * @Description: 根据一个权限的基本信息
	 * @param privilege
	 * @return
	 * @throws: TODO
	 */
	public int updatePrivilege(Privilege privilege);
	
	
	/**
	 * @Title: selectPrivById
	 * @Description: 根据id查找一个权限信息,不包含栏目的信息
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public Privilege selectPrivById(Integer id);
	
	
	/**
	 * @Title: selectPrivById
	 * @Description: 根据id查找一个权限信息,包含栏目集合的信息
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public Privilege selectPrivMenusById(Integer id);
	/**
	 * 查询所有的权限
	 * @return
	 */
	public List<Privilege> selectAllPrivileges();
	/**
	 * 查询出来所有的一级菜单权限
	 * @return
	 */
	public List<Privilege> selectAllMenuPrivileges();
	/**
	 * 查询出来所有的二级菜单
	 * @param privilege
	 * @return
	 */
	public List<Privilege> selectAllSubMenuPrivileges(Privilege privilege);
}