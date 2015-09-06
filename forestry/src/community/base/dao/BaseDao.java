package community.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import core.support.BaseParameter;
import core.support.QueryResult;

/**
 * @CopyRight��http://www.netrust.cn/
 *
 * @Description: ���ͻ����.
 * 				This file Charset is GBK 
 * 				ʵ�������� org.apache.commons.beanutils.BeanUtils
 * 					   core.support.BaseParameter
 * 					   core.support.QueryResult
 * @Author: lazite 
 * @CreateTime: 2015��8��31�� ����7:55:37   
 * @ModifyBy: lazite 
 * @ModeifyTime: 2015��8��31�� ����7:55:37   
 * @ModifyDescription:
 * @Version:   V1.0
 */
public interface BaseDao<E> {
	/**
	 * @Title: persist
	 * @Description: ����/���ʵ��
	 * @param entity
	 * @throws: TODO
	 */
	public void persist(E entity);

	/**
	 * @Title: deleteByPK
	 * @Description: �������idɾ��ʵ����(������ɾ��)
	 * @param id
	 * @return
	 * @throws: TODO
	 */
	public boolean deleteByPK(Serializable... id);

	/**
	 * @Title: delete
	 * @Description: ɾ��һ��ָ������ʵ��(������ϵ�Ƿ�ɾ���Ӻ�ʵ��ӳ��)
	 * @param entity
	 * @throws: TODO
	 */
	public void delete(E entity);

	/**
	 * @Title: deleteByProperties
	 * @Description: ͨ��HQL���ִ������ɾ�����
	 * @param propName ʵ��������
	 * @param propValue ʵ������ֵ
	 * @throws: TODO
	 */
	public void deleteByProperties(String propName, Object propValue);

	/**
	 * @Title: deleteByProperties
	 * @Description: ͨ��HQL���ִ������ɾ�����(������)
	 * @param propName ʵ��������
	 * @param propValue ʵ������ֵ
	 * @throws: TODO
	 */
	public void deleteByProperties(String[] propName, Object[] propValue);

	/**
	 * @Title: update
	 * @Description: �޸�ʵ�����.�ö�����Ϊ�Ѽ���״̬
	 * @param entity
	 * @throws: TODO
	 */
	public void update(E entity);

	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName ���������
	 * @param conditionValue �������ֵ
	 * @param propertyName �޸Ľ��������
	 * @param propertyValue �޸Ľ������ֵ
	 * @throws: TODO
	 */
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName, Object[] propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName ���������  conditionName where clause condiction property name
	 * @param conditionValue �������ֵ  conditionValue where clause condiction property value
	 * @param propertyName �޸Ľ��������  propertyName update clause property name array
	 * @param propertyValue �޸Ľ������ֵ  propertyValue update clase property value array
	 * @throws: TODO
	 */
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName ���������  conditionName where clause condiction property name
	 * @param conditionValue �������ֵ  conditionValue where clause condiction property value
	 * @param propertyName �޸Ľ��������  propertyName update clause property name array
	 * @param propertyValue �޸Ľ������ֵ  propertyValue update clase property value array
	 * @throws: TODO
	 */
	public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propertyValue);
	
	/**
	 * @Title: updateByProperties
	 * @Description: TODO
	 * @Simple  update T set conditionName1=conditionValue1 where 1=1 and propertyName1=propertyValue2
	 * @param conditionName ���������  conditionName where clause condiction property name
	 * @param conditionValue �������ֵ  conditionValue where clause condiction property value
	 * @param propertyName �޸Ľ��������  propertyName update clause property name array
	 * @param propertyValue �޸Ľ������ֵ  propertyValue update clase property value array
	 * @throws: TODO
	 */
	public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue);

	
	/**
	 * @Title: update
	 * @Description: ���oldPKִ��ɾ��֮��,����һ������
	 * @param entity �����޸ĵĶ���
	 * @param oldPK ��Ҫɾ��Ķ��������ʶ
	 * @throws: TODO
	 */
	public void update(E entity, Serializable oldPK);

	/**
	 * @Title: merge
	 * @Description: ���ʵ���״̬�ϲ�����ǰ�־û�������
	 * @param entity
	 * @return
	 * @throws: TODO
	 */
	public E merge(E entity);

	/**
	 * @Title: get��ʽ���ض���
	 * @Description: hibernate��ȷ��һ�¸�id��Ӧ������Ƿ����,������session�����в���,
	 * 			Ȼ���ڶ��������в���,��û�оͲ�ѯ��ݿ⣬��ݿ���û�оͷ���null.
	 * 			-��֧��LAZY
	 * @param id
	 * @return
	 * @throws: null
	 */
	public E get(Serializable id);

	/**
	 * @Title: load
	 * @Description: ���ӳ���ļ����༶���lazy���Ե�����(Ĭ��Ϊtrue)
	 * 			��Ϊtrue,��������Session�����в��ң�������id��Ӧ�Ķ��� �Ƿ� ����,
	 * 			��������ʹ���ӳټ��أ�����ʵ��Ĵ��������(�ô�����Ϊʵ��������࣬��CGLIB��̬���)
	 * 			�ȵ�����ʹ�øö���(���ȡOID����)��ʱ�� �ٲ�ѯ�����������ݿ�.����û�����׳��쳣
	 * 			load�����ɷ���û�м���ʵ����ݵĴ�����ʵ��
	 * 			֧��LAZY
	 * @param id �����л���id
	 * @return
	 * @throws: ObjectNotFoundException
	 */
	public E load(Serializable id);

	/**
	 * @Title: getByProerties
	 * @Description: ͨ������������ȡһ������
	 * @param propName ����������
	 * @param propValue	��������ֵ
	 * @return
	 * @throws: TODO
	 */
	public E getByProerties(String[] propName, Object[] propValue);
	
	/**
	 * @Title: getByProerties
	 * @Description: ͨ������������ȡһ������
	 * @param propName ����������
	 * @param propValue	��������ֵ
	 * @param sortedCondition ��������.��Ϊ�����ֶ�,ֵΪ������(desc or asc)
	 * @return
	 * @throws: TODO
	 */
	public E getByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);

	/**
	 * @Title: getByProerties
	 * @Description: ͨ������������ȡһ������
	 * @param propName ����������
	 * @param propValue	��������ֵ
	 * @return
	 * @throws: TODO
	 */
	public E getByProerties(String propName, Object propValue);
	
	/**
	 * @Title: getByProerties
	 * @Description: ͨ������������ȡһ������
	 * @param propName ����������
	 * @param propValue	��������ֵ
	 * @param sortedCondition ��������.��Ϊ�����ֶ�,ֵΪ������(desc or asc)
	 * @return
	 * @throws: TODO
	 */
	public E getByProerties(String propName, Object propValue, Map<String, String> sortedCondition);

	/**
	 * @Title: queryByProerties
	 * @Description: ����������������ѯһ�����󼯺�
	 * @param propName ����������
	 * @param propValue ��������ֵ
	 * @param sortedCondition Map �������������
	 * @param top �ӵ�1�����ȡ����
	 * @return
	 * @throws: TODO
	 */
	public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition, Integer top);
	
	/**
	 * @Title: queryByProerties
	 * @Description: ����������������ѯһ�����󼯺�
	 * @param propName ����������
	 * @param propValue ��������ֵ
	 * @param sortedCondition Map �������������
	 * @return
	 * @throws: TODO
	 */
	public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);
	
	/**
	 * @Title: queryByProerties
	 * @Description: ����������������ѯһ�����󼯺�
	 * @param propName ����������
	 * @param propValue ��������ֵ
	 * @param top �ӵ�1�����ȡ����
	 * @return
	 * @throws: TODO
	 */
	public List<E> queryByProerties(String[] propName, Object[] propValue, Integer top);

	/**
	 * @Title: queryByProerties
	 * @Description: ����������������ѯһ�����󼯺�
	 * @param propName ����������
	 * @param propValue ��������ֵ
	 * @return
	 * @throws: TODO
	 */
	public List<E> queryByProerties(String[] propName, Object[] propValue);
	
	/**
	 * @Title: queryByProerties
	 * @Description: ����������������ѯһ�����󼯺�
	 * @param propName ����������
	 * @param propValue ��������ֵ
	 * @param sortedCondition Map �������������
	 * @param top �ӵ�1�����ȡ����
	 * @return
	 * @throws: TODO
	 */
	public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top);

	/**
	 * @Title: queryByProerties
	 * @Description: ����������������ѯһ�����󼯺�
	 * @param propName ����������
	 * @param propValue ��������ֵ
	 * @param sortedCondition Map �������������
	 * @return
	 * @throws: TODO
	 */
	public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition);
	
	/**
	 * @Title: queryByProerties
	 * @Description: ����������������ѯһ�����󼯺�
	 * @param propName ����������
	 * @param propValue ��������ֵ
	 * @param top �ӵ�1�����ȡ����
	 * @return
	 * @throws: TODO
	 */
	public List<E> queryByProerties(String propName, Object propValue, Integer top);

	/**
	 * @Title: queryByProerties
	 * @Description: ����������������ѯһ�����󼯺�
	 * @param propName ����������
	 * @param propValue ��������ֵ
	 * @return
	 * @throws: TODO
	 */
	public List<E> queryByProerties(String propName, Object propValue);

	/**
	 * @Title: clear
	 * @Description: ���session����
	 * @throws: TODO
	 */
	public void clear();

	/**
	 * @Title: evict
	 * @Description: Remove this instance from the session cache
	 * 			�������session���������session��EntityEntries��������� 
	 * 			��ϸ�ο�:http://langhua9527.iteye.com/blog/343311
	 * @param entity
	 * @throws: TODO
	 */
	public void evict(E entity);

	/**
	 * @Title: countAll
	 * @Description: ͳ�����зǶ����ظ�����(�ظ����ݶ���equals��)
	 * @return
	 * @throws: TODO
	 */
	public Long countAll();

	/**
	 * @Title: doQueryAll
	 * @Description: Criteria��ʽ��ѯ����
	 * @return
	 * @throws: TODO
	 */
	public List<E> doQueryAll();
	
	/**
	 * @Title: doQueryAll
	 * @Description: Criteria��ʽ��ѯ
	 * @param sortedCondition ��������
	 * @param top �ӵ�һ����ʼ���top�����
	 * @return
	 * @throws: TODO
	 */
	public List<E> doQueryAll(Map<String, String> sortedCondition, Integer top);
	
	/**
	 * @Title: doQueryAll
	 * @Description: Criteria��ʽ��ѯĬ������
	 * @param top �ӵ�һ����ʼ���top�����
	 * @return
	 * @throws: TODO
	 */
	public List<E> doQueryAll(Integer top);
	
	/**
	 * @Title: doCount
	 * @Description: criteria��ѯ��������Ķ������
	 * @param parameter
	 * @return
	 * @throws: TODO
	 */
	public Long doCount(BaseParameter parameter);
	
	/**
	 * @Title: doQuery
	 * @Description: criteria��ѯ��������Ķ��󼯺�
	 * @param parameter
	 * @return
	 * @throws: TODO
	 */
	public List<E> doQuery(BaseParameter parameter);
	
	/**
	 * @Title: doPaginationQuery
	 * @Description: criteria��ҳ��ѯ��������Ķ��󼯺�.
	 * 				����װΪQueryResult<E>
	 * @param parameter
	 * @return
	 * @throws: TODO
	 */
	public QueryResult<E> doPaginationQuery(BaseParameter parameter);
	
	/**
	 * @Title: doPaginationQuery
	 * @Description: criteria��ҳ��ѯ��������Ķ��󼯺�.
	 * 				����װΪQueryResult<E>
	 * @param parameter
	 * @param bool ��δ���׺��ֺ���???????????
	 * @return
	 * @throws: TODO
	 */
	public QueryResult<E> doPaginationQuery(BaseParameter parameter, boolean bool);

}
