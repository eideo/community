package commnuity.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import core.support.BaseParameter;
import core.support.QueryResult;

/**
 * @CopyRight：http://www.netrust.cn/
 *
 * @Description: 泛型基础操作.
 * 				This file Charset is GBK 
 * @Author: lazite 
 * @CreateTime: 2015年8月31日 下午7:55:37   
 * @ModifyBy: lazite 
 * @ModeifyTime: 2015年8月31日 下午7:55:37   
 * @ModifyDescription:
 * @Version:   V1.0
 */

public interface BaseDao<E> {
	/**
	 * @Title: persist
	 * @Description: 保存/添加实体
	 * @param entity
	 * @throws: TODO
	 */
	public void persist(E entity);

	/**
	 * @Title: deleteByPK
	 * @Description: 根据主键id删除实体类(非批量删除)
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public boolean deleteByPK(Serializable... id);

	/**
	 * @Title: delete
	 * @Description: 删除一个指定对象实体(关联关系是否删除视乎实体映射)
	 * @param entity
	 * @throws: TODO
	 */
	public void delete(E entity);

	/**
	 * @Title: deleteByProperties
	 * @Description: 通过HQL语句执行批量删除操作
	 * @param propName 实体条件名
	 * @param propValue 实体条件值
	 * @throws: TODO
	 */
	public void deleteByProperties(String propName, Object propValue);

	/**
	 * @Title: deleteByProperties
	 * @Description: 通过HQL语句执行批量删除操作(多条件)
	 * @param propName 实体条件名
	 * @param propValue 实体条件值
	 * @throws: TODO
	 */
	public void deleteByProperties(String[] propName, Object[] propValue);

	/**
	 * @Title: update
	 * @Description: 修改实体对象.该对象需为已加载状态
	 * @param entity
	 * @throws: TODO
	 */
	public void update(E entity);

	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 语句条件名
	 * @param conditionValue 语句条件值
	 * @param propertyName 修改结果集属性名
	 * @param propertyValue 修改结果集属性值
	 * @throws: TODO
	 */
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName, Object[] propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 语句条件名  conditionName where clause condiction property name
	 * @param conditionValue 语句条件值  conditionValue where clause condiction property value
	 * @param propertyName 修改结果集属性名  propertyName update clause property name array
	 * @param propertyValue 修改结果集属性值  propertyValue update clase property value array
	 * @throws: TODO
	 */
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 语句条件名  conditionName where clause condiction property name
	 * @param conditionValue 语句条件值  conditionValue where clause condiction property value
	 * @param propertyName 修改结果集属性名  propertyName update clause property name array
	 * @param propertyValue 修改结果集属性值  propertyValue update clase property value array
	 * @throws: TODO
	 */
	public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 语句条件名  conditionName where clause condiction property name
	 * @param conditionValue 语句条件值  conditionValue where clause condiction property value
	 * @param propertyName 修改结果集属性名  propertyName update clause property name array
	 * @param propertyValue 修改结果集属性值  propertyValue update clase property value array
	 * @throws: TODO
	 */
	public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue);

	
	/**
	 * @Title: update
	 * @Description: 根据oldPK执行删除之后,新增一个对象
	 * @param entity 新增修改的对象
	 * @param oldPK 需要删除的对象主键标识
	 * @throws: TODO
	 */
	public void update(E entity, Serializable oldPK);

	/**
	 * @Title: merge
	 * @Description: 给定的实体的状态合并到当前持久化上下文
	 * @param entity
	 * @return
	 * @throws: TODO
	 */
	public E merge(E entity);

	/**
	 * @Title: get方式加载对象
	 * @Description: hibernate会确认一下该id对应的数据是否存在,首先在session缓存中查找,
	 * 					然后在二级缓存中查找,还没有就查询数据库，数据库中没有就返回null.
	 * 					-不支持LAZY
	 * @param id
	 * @return
	 * @throws: null
	 */
	public E get(Serializable id);

	/**
	 * @Title: load
	 * @Description: 根据映射文件上类级别的lazy属性的配置(默认为true)
	 * 					若为true,则首先在Session缓存中查找，看看该id对应的对象 是否 存在,
	 * 					不存在则使用延迟加载，返回实体的代理类对象(该代理类为实体类的子类，由CGLIB动态生成)
	 * 					等到具体使用该对象(除获取OID以外)的时候， 再查询二级缓存和数据库.若仍没发现抛出异常
	 * 					load方法可返回没有加载实体数据的代理类实例
	 * 					支持LAZY
	 * @param id 可序列化的id
	 * @return
	 * @throws: ObjectNotFoundException
	 */
	public E load(Serializable id);

	/**
	 * @Title: getByProerties
	 * @Description: 通过属性条件获取一个对象
	 * @param propName 条件属性名
	 * @param propValue	条件属性值
	 * @return
	 * @throws: TODO
	 */
	public E getByProerties(String[] propName, Object[] propValue);
	
	/**
	 * @Title: getByProerties
	 * @Description: 通过属性条件获取一个对象
	 * @param propName 条件属性名
	 * @param propValue	条件属性值
	 * @param sortedCondition 排序条件.键为排序字段,值为排序安排(desc or asc)
	 * @return
	 * @throws: TODO
	 */
	public E getByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);

	/**
	 * @Title: getByProerties
	 * @Description: 通过属性条件获取一个对象
	 * @param propName 条件属性名
	 * @param propValue	条件属性值
	 * @return
	 * @throws: TODO
	 */
	public E getByProerties(String propName, Object propValue);
	
	/**
	 * @Title: getByProerties
	 * @Description: 通过属性条件获取一个对象
	 * @param propName 条件属性名
	 * @param propValue	条件属性值
	 * @param sortedCondition 排序条件.键为排序字段,值为排序安排(desc or asc)
	 * @return
	 * @throws: TODO
	 */
	public E getByProerties(String propName, Object propValue, Map<String, String> sortedCondition);

	/**
	 * query by property
	 * 
	 * @param propName
	 * @param propValue
	 * @return
	 */
	public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition, Integer top);

	public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);

	public List<E> queryByProerties(String[] propName, Object[] propValue, Integer top);

	public List<E> queryByProerties(String[] propName, Object[] propValue);

	public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top);

	public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition);

	public List<E> queryByProerties(String propName, Object propValue, Integer top);

	public List<E> queryByProerties(String propName, Object propValue);

	/**
	 * Completely clear the session.
	 */
	public void clear();

	/**
	 * Remove this instance from the session cache.
	 */
	public void evict(E entity);

	/**
	 * count all
	 * 
	 * @return
	 */
	public Long countAll();

	/**
	 * Query all
	 * 
	 * @return
	 */
	public List<E> doQueryAll();

	public List<E> doQueryAll(Map<String, String> sortedCondition, Integer top);

	public List<E> doQueryAll(Integer top);

	public Long doCount(BaseParameter parameter);

	public List<E> doQuery(BaseParameter parameter);

	public QueryResult<E> doPaginationQuery(BaseParameter parameter);

	public QueryResult<E> doPaginationQuery(BaseParameter parameter, boolean bool);

}
