package community.isecrity.dao;

import java.util.List;

import com.hadoop.secrity.entity.Menu;

/**
 * @CopyRight：http://www.netrust.cn/
 *
 * @Description:操作栏目的dao层   
 * @Author: lazite 
 * @CreateTime: 2015年7月9日 下午1:54:35   
 * @ModifyBy: lazite 
 * @ModeifyTime: 2015年7月9日 下午1:54:35   
 * @ModifyDescription:
 * @Version:   V1.0
 */
public interface MenuDao {
	/**
	 * @Title: addMenu
	 * @Description: 插入栏目
	 * @param menu
	 * @return 返回插入的menu的id
	 * @throws: 插入失败返回值为0
	 */
    public int addMenu(Menu menu);
	
    /**
     * @Title: delMenuById
     * @Description: 根据一个id删除一个栏目
     * @param id
     * @return
     * @throws: TODO
     */
    public int delMenuById(Integer id);
    
    /**
     * @Title: updateMenu
     * @Description: 更新一个栏目的基本信息,不包括id
     * @param menu
     * @return
     * @throws: TODO
     */
    public int updateMenu(Menu menu);
    
    /**
     * @Title: selectMenuById
     * @Description: 根据一个id查找一个栏目信息
     * @param mId
     * @return
     * @throws: TODO
     */
    public Menu selectMenuById(Integer mId);

    /**
     * @Title: selectMenuById
     * @Description: 根据一个栏目name查找一个栏目信息
     * @param mId
     * @return
     * @throws: TODO
     */
    public Menu selectMenuByName(String name);
    
    /**
     * 
     * @Title: selectChildMenu
     * @Description: 查找子栏目
     * @param pId
     * @return
     * @throws: TODO
     */
    public List<Menu> selectChildMenu(Integer pId);
    
    /**
     * @Title: selectAllChildMenu
     * @Description: 根据父栏目的id查询父栏目下的所有子栏目
     * @param list
     * @return
     * @throws: TODO
     */
	public List<Menu> selectAllPChildMenu(List<Integer> list);
}