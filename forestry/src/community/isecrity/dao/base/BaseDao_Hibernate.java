package community.isecrity.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import community.isecrity.dao.base.support.BaseParameter;
import community.isecrity.dao.base.support.QueryResult;

/**
 * @CopyRight：http://www.netrust.cn/
 *
 * @Description: 泛型操作BaseDao_Hibernate
 * @Author: lazite 
 * @CreateTime: 2015年7月11日 下午6:37:07   
 * @ModifyBy: lazite 
 * @ModeifyTime: 2015年7月11日 下午6:37:07   
 * @ModifyDescription:
 * @Version:   V1.0
 */
public interface BaseDao_Hibernate<T> extends BaseDao<T>{
	/**
	 * @Title: merge
	 * @Description: merge方式保存对象
	 * @param entity
	 * @return
	 * @throws: TODO
	 */
	public T merge(T entity);
	
	/**
	 * @Title: addBatchJdbc
	 * @Description: 通过hibernate获取jdbc连接方式执行批量插入,则此处事务需要独立控制
	 * @param list
	 * @throws: TODO
	 */
	public void addBatchJdbc(List<T> list);
	
	/**
	 * @Title: deleteByPK
	 * @Description: 根据id删除序列化对象
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public boolean deleteByPK(Serializable... id);

	/**
	 * @Title: deleteByProperties
	 * @Description: 根据对象属性条件删除指定的对象
	 * @param propName hibernate对象的属性名,需要与sql字段已建立对应关系
	 * @param propValue 属性字段的值
	 * @throws: TODO
	 */
	public void deleteByProperties(String propName, Object propValue);

	/**
	 * @Title: deleteByProperties
	 * @Description: 根据对象多个属性条件删除指定的对象
	 * @param propName hibernate对象的属性名,需要与sql字段已建立对应关系
	 * @param propValue 属性字段的值
	 * @throws: TODO
	 */
	public void deleteByProperties(String[] propName, Object[] propValue);

	/**
	 * @Title: updateByProperties
	 * @Description: 更新满足条件对象的某些值
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 更新的属性字段名
	 * @param conditionValue 更新的属性字段值
	 * @param propertyName 更新条件的属性字段名
	 * @param propertyValue 更新条件的属性字段值
	 * @throws: TODO
	 */
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName, Object[] propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: 更新满足条件对象的某些值
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 更新的属性字段名
	 * @param conditionValue 更新的属性字段值
	 * @param propertyName 更新条件的属性字段名
	 * @param propertyValue 更新条件的属性字段值
	 * @throws: TODO
	 */
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 更新的属性字段名
	 * @param conditionValue 更新的属性字段值
	 * @param propertyName 更新条件的属性字段名
	 * @param propertyValue 更新条件的属性字段值
	 * @throws: TODO
	 */
	public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 更新的属性字段名
	 * @param conditionValue 更新的属性字段值
	 * @param propertyName 更新条件的属性字段名
	 * @param propertyValue 更新条件的属性字段值
	 * @throws: TODO
	 */
	public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue);

	/**
	 * @Title: update
	 * @Description: 修改序列化的对象,先删除再保存
	 * @param entity 需要修改的对象
	 * @param oldPK 修改对象的序列化键
	 * @throws: TODO
	 */
	public void update(T entity, Serializable oldPK);

	/**
	 * @Title: get方式检索对象
	 * @Description:get方法检索不到的话会返回null
	 * 				get方法和find方法都是直接从数据库中检索 。
	 * 				hibernate会确认一下该id对应的数据是否存在
	 * 				首先在session缓存中查找，然后在二级缓存中查找，还没有就查数据库，数据库中没有就返回null.
	 * @param id
	 * @return
	 */
	public T get(Serializable id);
	
	/**
	 * @Title: load
	 * @Description: 根据映射文件上类级别的lazy属性的配置(默认为true)，分情况讨论
	 * 			(1)若为true,则首先在Session缓存中查找，看看该id对应的对象是否存在，不存在则使用延迟加载，
	 * 				返回实体的代理类对象(该代理类为实体类的子类，由CGLIB动态生成)。
	 * 				等到具体使用该对象(除获取OID以外)的时候，再查询二级缓存和数据库，
	 * 				若仍没发现符合条件的记录，则会抛出一个ObjectNotFoundException。
	 * 			(2)若为false,就跟Hibernate get方法查找顺序一样，
	 * 				只是最终若没发现符合条件的记录，则会抛出一个ObjectNotFoundException
	 * 			(3)load方法创建时首先查询session缓存，没有就创建代理，实际使用数据时才查询二级缓存和数据库
	 * @param id 序列化主键id
	 * @return
	 * @throws: ObjectNotFoundException
	 */
	public T load(Serializable id);

	/**
	 * @Title: getByProerties
	 * @Description: 通过对象的属性条件获取一个对象
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @return
	 * @throws: TODO
	 */
	public T getByProerties(String[] propName, Object[] propValue);
	
	/**
	 * @Title: getByProerties
	 * @Description: 通过对象的属性条件获取一个对象
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @param sortedCondition 排序属性(desc or asc)
	 * @return
	 * @throws: TODO
	 */
	public T getByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);

	/**
	 * @Title: getByProerties
	 * @Description: 通过对象的属性条件获取一个对象
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @return
	 * @throws: TODO
	 */
	public T getByProerties(String propName, Object propValue);
	
	/**
	 * @Title: getByProerties
	 * @Description: 通过对象的属性条件获取一个对象
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @param sortedCondition 排序属性(desc or asc)
	 * @return
	 * @throws: TODO
	 */
	public T getByProerties(String propName, Object propValue, Map<String, String> sortedCondition);

	/**
	 * @Title: queryByProerties
	 * @Description:  通过对象的属性条件获取对象集合
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @param sortedCondition Map 排序属性(desc or asc)
	 * @param top 前多少条
	 * @return List<T>
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition, Integer top);
	
	/**
	 * @Title: queryByProerties
	 * @Description:  通过对象的属性条件获取对象集合
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @param sortedCondition Map 排序属性(desc or asc)
	 * @return List<T>
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);
	
	/**
	 * @Title: queryByProerties
	 * @Description:  通过对象的属性条件获取对象集合
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @param top 前多少条
	 * @return List<T>
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String[] propName, Object[] propValue, Integer top);

	/**
	 * @Title: queryByProerties
	 * @Description:  通过对象的属性条件获取对象集合
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @return List<T>
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String[] propName, Object[] propValue);
	
	/**
	 * @Title: queryByProerties
	 * @Description:  通过对象的属性条件获取对象集合
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @param sortedCondition Map 排序属性(desc or asc)
	 * @param top 前多少条
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top);

	/**
	 * @Title: queryByProerties
	 * @Description:  通过对象的属性条件获取对象集合
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @param sortedCondition Map 排序属性(desc or asc)
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition);
	
	/**
	 * @Title: queryByProerties
	 * @Description:  通过对象的属性条件获取对象集合
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @param top 前多少条
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String propName, Object propValue, Integer top);

	/**
	 * @Title: queryByProerties
	 * @Description:  通过对象的属性条件获取对象集合
	 * @param propName 对象的条件的属性名
	 * @param propValue	对象的条件的属性值
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String propName, Object propValue);

	/**
	 * @Title: clear
	 * @Description:  1 session.flush()的作用就是将session的缓存中的数据与数据库同步。
	 * 					Flush方法是可以设置的，也就是fulsh什么时候执行是可以设置的
	 *  				在session.beginTransaction前设置FlushMode
	 *  				session.setFlushMode(FlushMode.Always|AUTO|COMMIT|NEVER|MANUAL)
	 * 					Always:任何代码都会Flush
	 * 					AUTO:默认方式–自动
	 * 					Commit:COMMIT时
	 * 					Never:始终不
	 * 					MANUAL:手动方式
	 * 				  2 session.clear()的作用就是清除session中的缓存数据（不管缓存与数据库的同步）。
	 * 				  	当对象使用native生成器来生成主键id时，当执行session.save()方法时,就会立马往数据库插入一条数据,
	 * 				  	这时候执行session.clear()也不会影响。
	 * @throws: TODO
	 */
	public void clear();

	/**
	 * @Title: evict
	 * @Description: Remove this instance from the session cache
	 * 			从session缓存(EntityEntries属性)中逐出该对象
	 * 			详细参考:http://langhua9527.iteye.com/blog/343311
	 * @param entity
	 * @throws: TODO
	 */
	public void evict(T entity);

	/**
	 * @Title: countAll
	 * @Description: 查询数据总数量 count(*)
	 * @return
	 * @throws: TODO
	 */
	public Long countAll();

	/**
	 * @Title: doQueryAll
	 * @Description: Criteria方式查询数据对象集合
	 * @return count(*)
	 * @throws: TODO
	 */
	public List<T> doQueryAll();
	
	/**
	 * @Title: doQueryAll
	 * @Description: Criteria方式查询数据对象集合
	 * @param sortedCondition Map 排序属性(desc or asc)
	 * @param top 前多少条
	 * @return
	 * @throws: TODO
	 */
	public List<T> doQueryAll(Map<String, String> sortedCondition, Integer top);
	
	/**
	 * @Title: doQueryAll
	 * @Description: Criteria方式查询数据对象集合
	 * @param top 前多少条
	 * @return
	 * @throws: TODO
	 */
	public List<T> doQueryAll(Integer top);
	
	/**
	 * @Title: doCount
	 * @Description: criteria方式查询符合BaseParameter的数据数量
	 * @param parameter
	 * @return
	 * @throws: TODO
	 */
	public Long doCount(BaseParameter parameter);
	
	/**
	 * @Title: doQuery
	 * @Description: criteria方式查询符合BaseParameter的数据对象集合
	 * @param parameter
	 * @return
	 * @throws: TODO
	 */
	public List<T> doQuery(BaseParameter parameter);
	
	/**
	 * @Title: doPaginationQuery
	 * @Description: criteria方式分页查询符合BaseParameter的数据对象集合
	 * 				结果集封装为QueryResult<T>
	 * @param parameter
	 * @return
	 * @throws: TODO
	 */
	public QueryResult<T> doPaginationQuery(BaseParameter parameter);
	
	/**
	 * @Title: doPaginationQuery
	 * @Description:  criteria方式分页查询符合BaseParameter的数据对象集合
	 * 				结果集封装为QueryResult<T>
	 * @param parameter
	 * @param bool 此参数是何种含义暂未确定???????????
	 * @return
	 * @throws: TODO
	 */
	public QueryResult<T> doPaginationQuery(BaseParameter parameter, boolean bool);

}
