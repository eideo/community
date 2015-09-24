package community.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import core.support.BaseParameter;
import core.support.QueryResult;

/**
 * @CopyRight锟斤拷http://www.netrust.cn/
 *
 * @Description: 锟斤拷锟酵伙拷锟斤拷锟�
 * 				This file Charset is GBK 
 * 				实锟斤拷锟斤拷锟斤拷锟斤拷 org.apache.commons.beanutils.BeanUtils
 * 					   core.support.BaseParameter
 * 					   core.support.QueryResult
 * @Author: lazite 
 * @CreateTime: 2015锟斤拷8锟斤拷31锟斤拷 锟斤拷锟斤拷7:55:37   
 * @ModifyBy: lazite 
 * @ModeifyTime: 2015锟斤拷8锟斤拷31锟斤拷 锟斤拷锟斤拷7:55:37   
 * @ModifyDescription:
 * @Version:   V1.0
 */
public interface BaseDao<T> {
	/**
	 * @Title: persist
	 * @Description: 锟斤拷锟斤拷/锟斤拷锟绞碉拷锟�
	 * @param entity
	 * @throws: TODO
	 */
	public void persist(T entity);

	/**
	 * @Title: deleteByPK
	 * @Description: 锟斤拷锟斤拷锟斤拷锟絠d删锟斤拷实锟斤拷锟斤拷(锟斤拷锟斤拷锟斤拷删锟斤拷)
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public boolean deleteByPK(Serializable... id);

	/**
	 * @Title: delete
	 * @Description: 删锟斤拷一锟斤拷指锟斤拷锟斤拷锟斤拷实锟斤拷(锟斤拷锟斤拷锟斤拷系锟角凤拷删锟斤拷锟接猴拷实锟斤拷映锟斤拷)
	 * @param entity
	 * @throws: TODO
	 */
	public void delete(T entity);

	/**
	 * @Title: deleteByProperties
	 * @Description: 通锟斤拷HQL锟斤拷锟街达拷锟斤拷锟斤拷锟缴撅拷锟斤拷锟斤拷
	 * @param propName 实锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue 实锟斤拷锟斤拷锟斤拷值
	 * @throws: TODO
	 */
	public void deleteByProperties(String propName, Object propValue);

	/**
	 * @Title: deleteByProperties
	 * @Description: 通锟斤拷HQL锟斤拷锟街达拷锟斤拷锟斤拷锟缴撅拷锟斤拷锟斤拷(锟斤拷锟斤拷锟斤拷)
	 * @param propName 实锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue 实锟斤拷锟斤拷锟斤拷值
	 * @throws: TODO
	 */
	public void deleteByProperties(String[] propName, Object[] propValue);

	/**
	 * @Title: update
	 * @Description: 锟睫革拷实锟斤拷锟斤拷锟�锟矫讹拷锟斤拷锟斤拷为锟窖硷拷锟斤拷状态
	 * @param entity
	 * @throws: TODO
	 */
	public void update(T entity);

	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 * @param conditionValue 锟斤拷锟斤拷锟斤拷锟街�
	 * @param propertyName 锟睫改斤拷锟斤拷锟斤拷锟斤拷锟�
	 * @param propertyValue 锟睫改斤拷锟斤拷锟斤拷锟街�
	 * @throws: TODO
	 */
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName, Object[] propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 锟斤拷锟斤拷锟斤拷锟斤拷锟� conditionName where clause condiction property name
	 * @param conditionValue 锟斤拷锟斤拷锟斤拷锟街� conditionValue where clause condiction property value
	 * @param propertyName 锟睫改斤拷锟斤拷锟斤拷锟斤拷锟� propertyName update clause property name array
	 * @param propertyValue 锟睫改斤拷锟斤拷锟斤拷锟街� propertyValue update clase property value array
	 * @throws: TODO
	 */
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 锟斤拷锟斤拷锟斤拷锟斤拷锟� conditionName where clause condiction property name
	 * @param conditionValue 锟斤拷锟斤拷锟斤拷锟街� conditionValue where clause condiction property value
	 * @param propertyName 锟睫改斤拷锟斤拷锟斤拷锟斤拷锟� propertyName update clause property name array
	 * @param propertyValue 锟睫改斤拷锟斤拷锟斤拷锟街� propertyValue update clase property value array
	 * @throws: TODO
	 */
	public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName 锟斤拷锟斤拷锟斤拷锟斤拷锟� conditionName where clause condiction property name
	 * @param conditionValue 锟斤拷锟斤拷锟斤拷锟街� conditionValue where clause condiction property value
	 * @param propertyName 锟睫改斤拷锟斤拷锟斤拷锟斤拷锟� propertyName update clause property name array
	 * @param propertyValue 锟睫改斤拷锟斤拷锟斤拷锟街� propertyValue update clase property value array
	 * @throws: TODO
	 */
	public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue);

	
	/**
	 * @Title: update
	 * @Description: 锟斤拷锟給ldPK执锟斤拷删锟斤拷之锟斤拷,锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷
	 * @param entity 锟斤拷锟斤拷锟睫改的讹拷锟斤拷
	 * @param oldPK 锟斤拷要删锟斤拷亩锟斤拷锟斤拷锟斤拷锟斤拷识
	 * @throws: TODO
	 */
	public void update(T entity, Serializable oldPK);

	/**
	 * @Title: merge
	 * @Description: 锟斤拷锟绞碉拷锟斤拷状态锟较诧拷锟斤拷锟斤拷前锟街久伙拷锟斤拷锟斤拷锟斤拷
	 * @param entity
	 * @return
	 * @throws: TODO
	 */
	public T merge(T entity);

	/**
	 * @Title: get锟斤拷式锟斤拷锟截讹拷锟斤拷
	 * @Description: hibernate锟斤拷确锟斤拷一锟铰革拷id锟斤拷应锟斤拷锟斤拷锟斤拷欠锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷session锟斤拷锟斤拷锟叫诧拷锟斤拷,
	 * 			然锟斤拷锟节讹拷锟斤拷锟斤拷锟斤拷锟叫诧拷锟斤拷,锟斤拷没锟叫就诧拷询锟斤拷菘猓拷锟捷匡拷锟斤拷没锟叫就凤拷锟斤拷null.
	 * 			-锟斤拷支锟斤拷LAZY
	 * @param id
	 * @return
	 * @throws: null
	 */
	public T get(Serializable id);

	/**
	 * @Title: load
	 * @Description: 锟斤拷锟接筹拷锟斤拷募锟斤拷锟斤拷嗉讹拷锟斤拷lazy锟斤拷锟皆碉拷锟斤拷锟斤拷(默锟斤拷为true)
	 * 			锟斤拷为true,锟斤拷锟斤拷锟斤拷锟斤拷Session锟斤拷锟斤拷锟叫诧拷锟揭ｏ拷锟斤拷锟斤拷锟斤拷id锟斤拷应锟侥讹拷锟斤拷 锟角凤拷 锟斤拷锟斤拷,
	 * 			锟斤拷锟斤拷锟斤拷锟斤拷使锟斤拷锟接迟硷拷锟截ｏ拷锟斤拷锟斤拷实锟斤拷拇锟斤拷锟斤拷锟斤拷锟斤拷(锟矫达拷锟斤拷锟斤拷为实锟斤拷锟斤拷锟斤拷锟斤拷啵拷锟紺GLIB锟斤拷态锟斤拷锟�
	 * 			锟饺碉拷锟斤拷锟斤拷使锟矫该讹拷锟斤拷(锟斤拷锟饺ID锟斤拷锟斤拷)锟斤拷时锟斤拷 锟劫诧拷询锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟捷匡拷.锟斤拷锟斤拷没锟斤拷锟斤拷锟阶筹拷锟届常
	 * 			load锟斤拷锟斤拷锟缴凤拷锟斤拷没锟叫硷拷锟斤拷实锟斤拷锟斤拷莸拇锟斤拷锟斤拷锟绞碉拷锟�
	 * 			支锟斤拷LAZY
	 * @param id 锟斤拷锟斤拷锟叫伙拷锟斤拷id
	 * @return
	 * @throws: ObjectNotFoundException
	 */
	public T load(Serializable id);

	/**
	 * @Title: getByProerties
	 * @Description: 通锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷取一锟斤拷锟斤拷锟斤拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue	锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @return
	 * @throws: TODO
	 */
	public T getByProerties(String[] propName, Object[] propValue);
	
	/**
	 * @Title: getByProerties
	 * @Description: 通锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷取一锟斤拷锟斤拷锟斤拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue	锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @param sortedCondition 锟斤拷锟斤拷锟斤拷锟斤拷.锟斤拷为锟斤拷锟斤拷锟街讹拷,值为锟斤拷锟斤拷锟斤拷(desc or asc)
	 * @return
	 * @throws: TODO
	 */
	public T getByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);

	/**
	 * @Title: getByProerties
	 * @Description: 通锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷取一锟斤拷锟斤拷锟斤拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue	锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @return
	 * @throws: TODO
	 */
	public T getByProerties(String propName, Object propValue);
	
	/**
	 * @Title: getByProerties
	 * @Description: 通锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷取一锟斤拷锟斤拷锟斤拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue	锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @param sortedCondition 锟斤拷锟斤拷锟斤拷锟斤拷.锟斤拷为锟斤拷锟斤拷锟街讹拷,值为锟斤拷锟斤拷锟斤拷(desc or asc)
	 * @return
	 * @throws: TODO
	 */
	public T getByProerties(String propName, Object propValue, Map<String, String> sortedCondition);

	/**
	 * @Title: queryByProerties
	 * @Description: 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询一锟斤拷锟斤拷锟襟集猴拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue 锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @param sortedCondition Map 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 * @param top 锟接碉拷1锟斤拷锟斤拷锟饺★拷锟斤拷锟�
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition, Integer top);
	
	/**
	 * @Title: queryByProerties
	 * @Description: 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询一锟斤拷锟斤拷锟襟集猴拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue 锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @param sortedCondition Map 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);
	
	/**
	 * @Title: queryByProerties
	 * @Description: 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询一锟斤拷锟斤拷锟襟集猴拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue 锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @param top 锟接碉拷1锟斤拷锟斤拷锟饺★拷锟斤拷锟�
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String[] propName, Object[] propValue, Integer top);

	/**
	 * @Title: queryByProerties
	 * @Description: 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询一锟斤拷锟斤拷锟襟集猴拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue 锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String[] propName, Object[] propValue);
	
	/**
	 * @Title: queryByProerties
	 * @Description: 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询一锟斤拷锟斤拷锟襟集猴拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue 锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @param sortedCondition Map 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 * @param top 锟接碉拷1锟斤拷锟斤拷锟饺★拷锟斤拷锟�
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top);

	/**
	 * @Title: queryByProerties
	 * @Description: 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询一锟斤拷锟斤拷锟襟集猴拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue 锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @param sortedCondition Map 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition);
	
	/**
	 * @Title: queryByProerties
	 * @Description: 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询一锟斤拷锟斤拷锟襟集猴拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue 锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @param top 锟接碉拷1锟斤拷锟斤拷锟饺★拷锟斤拷锟�
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String propName, Object propValue, Integer top);

	/**
	 * @Title: queryByProerties
	 * @Description: 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询一锟斤拷锟斤拷锟襟集猴拷
	 * @param propName 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param propValue 锟斤拷锟斤拷锟斤拷锟斤拷值
	 * @return
	 * @throws: TODO
	 */
	public List<T> queryByProerties(String propName, Object propValue);

	/**
	 * @Title: clear
	 * @Description: 锟斤拷锟絪ession锟斤拷锟斤拷
	 * @throws: TODO
	 */
	public void clear();

	/**
	 * @Title: evict
	 * @Description: Remove this instance from the session cache
	 * 			锟斤拷锟斤拷锟斤拷锟絪ession锟斤拷锟斤拷锟斤拷锟斤拷锟絪ession锟斤拷EntityEntries锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 * 			锟斤拷细锟轿匡拷:http://langhua9527.iteye.com/blog/343311
	 * @param entity
	 * @throws: TODO
	 */
	public void evict(T entity);

	/**
	 * @Title: countAll
	 * @Description: 统锟斤拷锟斤拷锟叫非讹拷锟斤拷锟截革拷锟斤拷锟斤拷(锟截革拷锟斤拷锟捷讹拷锟斤拷equals锟斤拷)
	 * @return
	 * @throws: TODO
	 */
	public Long countAll();

	/**
	 * @Title: doQueryAll
	 * @Description: Criteria锟斤拷式锟斤拷询锟斤拷锟斤拷
	 * @return
	 * @throws: TODO
	 */
	public List<T> doQueryAll();
	
	/**
	 * @Title: doQueryAll
	 * @Description: Criteria锟斤拷式锟斤拷询
	 * @param sortedCondition 锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param top 锟接碉拷一锟斤拷锟斤拷始锟斤拷锟絫op锟斤拷锟斤拷锟�
	 * @return
	 * @throws: TODO
	 */
	public List<T> doQueryAll(Map<String, String> sortedCondition, Integer top);
	
	/**
	 * @Title: doQueryAll
	 * @Description: Criteria锟斤拷式锟斤拷询默锟斤拷锟斤拷锟斤拷
	 * @param top 锟接碉拷一锟斤拷锟斤拷始锟斤拷锟絫op锟斤拷锟斤拷锟�
	 * @return
	 * @throws: TODO
	 */
	public List<T> doQueryAll(Integer top);
	
	/**
	 * @Title: doCount
	 * @Description: criteria锟斤拷询锟斤拷锟斤拷锟斤拷锟斤拷亩锟斤拷锟斤拷锟斤拷
	 * @param parameter
	 * @return
	 * @throws: TODO
	 */
	public Long doCount(BaseParameter parameter);
	
	/**
	 * @Title: doQuery
	 * @Description: criteria锟斤拷询锟斤拷锟斤拷锟斤拷锟斤拷亩锟斤拷蠹锟�
	 * @param parameter
	 * @return
	 * @throws: TODO
	 */
	public List<T> doQuery(BaseParameter parameter);
	
	/**
	 * @Title: doPaginationQuery
	 * @Description: criteria锟斤拷页锟斤拷询锟斤拷锟斤拷锟斤拷锟斤拷亩锟斤拷蠹锟�
	 * 				锟斤拷锟斤拷装为QueryResult<E>
	 * @param parameter
	 * @return
	 * @throws: TODO
	 */
	public QueryResult<T> doPaginationQuery(BaseParameter parameter);
	
	/**
	 * @Title: doPaginationQuery
	 * @Description: criteria锟斤拷页锟斤拷询锟斤拷锟斤拷锟斤拷锟斤拷亩锟斤拷蠹锟�
	 * 				锟斤拷锟斤拷装为QueryResult<E>
	 * @param parameter
	 * @param bool 锟斤拷未锟斤拷锟阶猴拷锟街猴拷锟斤拷???????????
	 * @return
	 * @throws: TODO
	 */
	public QueryResult<T> doPaginationQuery(BaseParameter parameter, boolean bool);

}
