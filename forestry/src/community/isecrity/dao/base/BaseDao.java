package community.isecrity.dao.base;

import java.util.List;
import com.github.pagehelper.Page;

/**
 * @CopyRight：http://www.netrust.cn/
 *
 * @Description: 泛型操作baseDao
 * @Author: lazite 
 * @CreateTime: 2015年7月11日 下午6:37:07   
 * @ModifyBy: lazite 
 * @ModeifyTime: 2015年7月11日 下午6:37:07   
 * @ModifyDescription:
 * @Version:   V1.0
 */
public interface BaseDao<T> {
	/**
     * 对对象进行持久化操作
     * @param obj
     * @return
	 * @throws Exception 
     */
    public void add(T obj);
    
    /**
     * @Title: addBackId
     * @Description: 对对象进行持久化操作,返回持久化的对象Id
     * @param obj
     * @return
     * @throws: TODO
     */
    public int addBackId(T obj);
    
    /**
     * @Title: add
     * @Description: 批量插入多条对象数据
     * @param list
     * @throws: TODO
     */
    public void addBatch(List<T> list);
    
    /**
     * @Title: delete
     * @Description: 根据对象唯一条件删除一个对象
     * @param obj
     * @throws: TODO
     */
    public void delete(Object obj);

    /**
     * 删除指定持久化对象
     * @param id
     */
    public void deleteByT(T t);
    
    /**
     * 修改指定的持久化对象
     * @param id
     * @param obj
     */
    public void update(T obj);
    
    /**
     * @Title: updateBatch
     * @Description: 批量更新
     * @param list
     * @throws: TODO
     */
    public void updateBatch(List<T> list);
    
    /**
     * 返回持久化对象
     * @param T 唯一参数(主键获取唯一参数)
     * @return 找到则返回，否则返回空
     */
    public T load(Object obj);

    /**
     * @Title: getBean
     * @Description:  根据对象参数条件查找符合条件的对象
     * @param t
     * @return
     * @throws: TODO
     */
    public T getBean(T t);

    /**
     * @Title: getBeanList
     * @Description: 根据对象参数条件查找符合条件的列表对象
     * @param id
     * @return
     * @throws: TODO
     */
    public List<T> getBeanList(T t);
    
    /**
     * @Title: selectObj4Page
     * @Description: 查询分页结果
     * @param obj
     * @param page
     * @return
     * @throws: TODO
     */
    public Object getT4Page(Object obj,T t);
    
    /**
     * @Title: getTNums
     * @Description: 根据对象条件查找符合条件的对象量
     * @param t
     * @return
     * @throws: TODO
     */
    public long getTNums(T t);
}
