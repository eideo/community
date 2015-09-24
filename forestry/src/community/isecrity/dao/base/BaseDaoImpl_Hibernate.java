package community.isecrity.dao.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.springframework.util.ReflectionUtils;

import community.isecrity.dao.base.support.BaseParameter;
import community.isecrity.dao.base.support.QueryResult;


public class BaseDaoImpl_Hibernate<T> implements BaseDao_Hibernate<T>{
	private static Map<String, Method> MAP_METHOD = new HashMap<String, Method>();

	private SessionFactory sessionFactory;
	protected Class<T> entityClass;
	
	//***************************************************
	//以下为get/set
	//***************************************************
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	//手动注入
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}	
	//采用spring注入,非spring框架取消该注解
	@Resource(name = "sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	//***************************************************
	//以下构造函数
	//***************************************************
	public BaseDaoImpl_Hibernate() {
		super();
	}
	public BaseDaoImpl_Hibernate(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	//***************************************************
	//以下为继承baseDao的实现
	//***************************************************
	@Override
	public void add(T obj) {
		getSession().save(obj);
	}
	
	//需要主键建立生成策略非手动主键,需要主键属性名称,该主键属性名称暂定为id,后续通过参数或者对象内部属性实现自由变化
	//引入了spring的工具类包 org.springframework.util.ReflectionUtils
	@Override
	public int addBackId(T obj) {
		getSession().save(obj);
		return (int) ReflectionUtils.getField(ReflectionUtils.findField(obj.getClass(), "id"), obj);
	}
	
	//需要配置hibernate.jdbc.batch_size参数,建议30-50之间
	@Override
	public void addBatch(List<T> list){
		Transaction tx=getSession().beginTransaction();
		for(int i=0;i<list.size();i++){
			getSession().save(list.get(i));
			// 以每50个数据作为一个处理单元
			if(i%50==0){
				getSession().flush(); //将Hibernate缓存中的数据提交到数据库,保持与数据库数据的同步
				this.clear(); // 清除内部缓存的全部数据，及时释放出占用的内存
			}
		}
		tx.commit();
	}	
	
	//通过hibernate获取jdbc连接方式执行批量插入,则此处事务所有独立控制,暂未完成语句转化
	@Override
	public void addBatchJdbc(List<T> list) {
		Transaction tx=getSession().beginTransaction();
		//定义一个匿名类，实现了Work接口,hibernate3.2以后废弃了Session.connection()方法改用内部类实现
		Work work=new Work(){
			public void execute(Connection connection) throws SQLException{
				//通过JDBC API执行用于批量更新的SQL语句
				PreparedStatement stmt=connection.prepareStatement("inseter into "+entityClass.getName()+" values ()");
		
				stmt.executeUpdate();
			}
		};
		//执行work
		getSession().doWork(work);
		tx.commit();
	}
	
	//根据主键删除Integer/String/Long/int/long
	@Override
	public void delete(Object obj) {
		if(obj instanceof java.lang.Integer || obj.getClass().getSimpleName().equals("int"))
			this.deleteByPK((Integer) obj);
		else if(obj instanceof java.lang.String)
			this.deleteByPK((String) obj);
		else if(obj instanceof java.lang.Long || obj.getClass().getSimpleName().equals("long"))
			this.deleteByPK((Long) obj);
	}

	@Override
	public void deleteByT(T t) {
		Field[] fields = t.getClass().getDeclaredFields();//根据Class对象获得属性 私有的也可以获得
		List<String> propNames=null;
		List<Object> propValues=null;
		
		for (int i = 0; i < fields.length; i++) {
			try {
				fields[i].setAccessible(true);
				if(fields[i].get(t)!=null){
					propNames.add(fields[i].getName());
					propValues.add(fields[i].get(t));				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.deleteByProperties((String[])propNames.toArray(new String[propNames.size()]), propValues.toArray());
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void updateBatch(List<T> list) {
		Transaction tx=getSession().beginTransaction();
		for(int i=0;i<list.size();i++){
			getSession().update(list.get(i));
			// 以每50个数据作为一个处理单元
			if(i%50==0){
				getSession().flush(); //将Hibernate缓存中的数据提交到数据库,保持与数据库数据的同步
				this.clear(); // 清除内部缓存的全部数据，及时释放出占用的内存
			}
		}
		tx.commit();
	}

	@Override
	public T load(Object obj) {
		if(obj instanceof java.lang.Integer || obj.getClass().getSimpleName().equals("int"))
			return this.load((Integer) obj);
		else if(obj instanceof java.lang.String)
			return this.load((String) obj);
		else if(obj instanceof java.lang.Long || obj.getClass().getSimpleName().equals("long"))
			return this.load((Long) obj);
		return null;
	}

	@Override
	public T getBean(T t) {
		Field[] fields = t.getClass().getDeclaredFields();//根据Class对象获得属性 私有的也可以获得
		List<String> propNames=null;
		List<Object> propValues=null;
		
		for (int i = 0; i < fields.length; i++) {
			try {
				fields[i].setAccessible(true);
				if(fields[i].get(t)!=null){
					propNames.add(fields[i].getName());
					propValues.add(fields[i].get(t));				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return this.getByProerties((String[])propNames.toArray(new String[propNames.size()]), propValues.toArray());
	}

	@Override
	public List<T> getBeanList(T t) {
		Field[] fields = t.getClass().getDeclaredFields();//根据Class对象获得属性 私有的也可以获得
		List<String> propNames=null;
		List<Object> propValues=null;
		
		for (int i = 0; i < fields.length; i++) {
			try {
				fields[i].setAccessible(true);
				if(fields[i].get(t)!=null){
					propNames.add(fields[i].getName());
					propValues.add(fields[i].get(t));				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return this.queryByProerties((String[])propNames.toArray(new String[propNames.size()]), propValues.toArray());
	}

	@Override
	public Object getT4Page(Object obj, T t) {
		return doPaginationQuery((BaseParameter)obj);
	}

	@Override
	public long getTNums(T t) {
		return doCount((BaseParameter)t);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T merge(T t) {
		return (T) getSession().merge(t);
	}

	@Override
	public boolean deleteByPK(Serializable... id) {
		boolean result = false;
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				T entity = get(id[i]);
				if (entity != null) {
					getSession().delete(entity);
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public void deleteByProperties(String propName, Object propValue) {
		deleteByProperties(new String[] { propName }, new Object[] { propValue });
	}

	@Override
	public void deleteByProperties(String[] propName, Object[] propValue) {
		if (propName != null && propName.length > 0 && propValue != null && propValue.length > 0 && propValue.length == propName.length) {
			StringBuffer sb = new StringBuffer("delete from " + entityClass.getName() + " o where 1=1 ");
			appendQL(sb, propName, propValue);
			Query query = getSession().createQuery(sb.toString());
			setParameter(query, propName, propValue);
			query.executeUpdate();
		}
	}

	@Override
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName, Object[] propertyValue) {
		if (propertyName != null && propertyName.length > 0 && propertyValue != null && propertyValue.length > 0 && propertyName.length == propertyValue.length && conditionValue != null && conditionValue.length > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("update " + entityClass.getName() + " o set ");
			for (int i = 0; i < propertyName.length; i++) {
				sb.append(propertyName[i] + " = :p_" + propertyName[i] + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(" where 1=1 ");
			appendQL(sb, conditionName, conditionValue);
			Query query = getSession().createQuery(sb.toString());
			
			for (int i = 0; i < propertyName.length; i++) {
				query.setParameter("p_" + propertyName[i], propertyValue[i]);
			}
			setParameter(query, conditionName, conditionValue);
			query.executeUpdate();
		} else {
			throw new IllegalArgumentException("Method updateByProperties in BaseDao argument is illegal!");
		}
	}

	@Override
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue) {
		updateByProperties(conditionName, conditionValue, new String[] { propertyName }, new Object[] { propertyValue });
	}

	@Override
	public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propertyValue) {
		updateByProperties(new String[] { conditionName }, new Object[] { conditionValue }, propertyName, propertyValue);
	}

	@Override
	public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue) {
		updateByProperties(new String[] { conditionName }, new Object[] { conditionValue }, new String[] { propertyName }, new Object[] { propertyValue });
	}
	
	//先删除再保存
	@Override
	public void update(T entity, Serializable oldPK) {
		deleteByPK(oldPK);
		getSession().save(entity);
	}

	@Override
	public T get(Serializable id) {
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public T load(Serializable id) {
		return (T) getSession().load(entityClass, id);
	}

	@Override
	public T getByProerties(String[] propName, Object[] propValue) {
		return getByProerties(propName, propValue, null);
	}

	@Override
	public T getByProerties(String[] propName, Object[] propValue,Map<String, String> sortedCondition) {
		if (propName != null && propName.length > 0 && propValue != null && propValue.length > 0 && propValue.length == propName.length) {
			StringBuffer sb = new StringBuffer("select o from " + entityClass.getName() + " o where 1=1 ");
			appendQL(sb, propName, propValue);
			if (sortedCondition != null && sortedCondition.size() > 0) {
				sb.append(" order by ");
				for (Entry<String, String> e : sortedCondition.entrySet()) {
					sb.append(e.getKey() + " " + e.getValue() + ",");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			Query query = getSession().createQuery(sb.toString());
			setParameter(query, propName, propValue);
			List<T> list = query.list();
			if (list != null && list.size() > 0)
				return list.get(0);
		}
		return null;
	}

	@Override
	public T getByProerties(String propName, Object propValue) {
		return getByProerties(new String[] { propName }, new Object[] { propValue });
	}

	@Override
	public T getByProerties(String propName, Object propValue,Map<String, String> sortedCondition) {
		return getByProerties(new String[] { propName }, new Object[] { propValue }, sortedCondition);
	}

	@Override
	public List<T> queryByProerties(String[] propName, Object[] propValue,Map<String, String> sortedCondition, Integer top) {
		if (propName != null && propValue != null && propValue.length == propName.length) {
			StringBuffer sb = new StringBuffer("select o from " + entityClass.getName() + " o where 1=1 ");
			appendQL(sb, propName, propValue);
			if (sortedCondition != null && sortedCondition.size() > 0) {
				sb.append(" order by ");
				for (Entry<String, String> e : sortedCondition.entrySet()) {
					sb.append(e.getKey() + " " + e.getValue() + ",");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			Query query = getSession().createQuery(sb.toString());
			setParameter(query, propName, propValue);
			if (top != null) {
				query.setFirstResult(0);
				query.setMaxResults(top);
			}
			return query.list();
		}
		return null;
	}

	public List<T> queryByProerties(String[] propName, Object[] propValue, Integer top) {
		return queryByProerties(propName, propValue, null, top);
	}

	public List<T> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
		return queryByProerties(propName, propValue, sortedCondition, null);
	}

	public List<T> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top) {
		return queryByProerties(new String[] { propName }, new Object[] { propValue }, sortedCondition, top);
	}

	public List<T> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition) {
		return queryByProerties(new String[] { propName }, new Object[] { propValue }, sortedCondition, null);
	}

	public List<T> queryByProerties(String propName, Object propValue, Integer top) {
		return queryByProerties(new String[] { propName }, new Object[] { propValue }, null, top);
	}

	public List<T> queryByProerties(String[] propName, Object[] propValue) {
		return queryByProerties(propName, propValue, null, null);
	}

	public List<T> queryByProerties(String propName, Object propValue) {
		return queryByProerties(new String[] { propName }, new Object[] { propValue }, null, null);
	}

	@Override
	public void clear() {
		getSession().clear();
	}

	@Override
	public void evict(T entity) {
		getSession().evict(entity);
	}

	@Override
	public Long countAll() {
		return (Long) getSession().createQuery("select count(*) from " + entityClass.getName()).uniqueResult();
	}

	@Override
	public List<T> doQueryAll(Map<String, String> sortedCondition, Integer top) {
		Criteria criteria = getSession().createCriteria(entityClass);
		if (sortedCondition != null && sortedCondition.size() > 0) {
			for (Iterator<String> it = sortedCondition.keySet().iterator(); it.hasNext();) {
				String pm = it.next();
				if (BaseParameter.SORTED_DESC.equals(sortedCondition.get(pm))) {
					criteria.addOrder(Order.desc(pm));
				} else if (BaseParameter.SORTED_ASC.equals(sortedCondition.get(pm))) {
					criteria.addOrder(Order.asc(pm));
				}
			}
		}
		if (top != null) {
			criteria.setMaxResults(top);
			criteria.setFirstResult(0);
		}
		return criteria.list();
	}

	@Override
	public List<T> doQueryAll() {
		return doQueryAll(null, null);
	}

	@Override
	public List<T> doQueryAll(Integer top) {
		return doQueryAll(null, top);
	}

	@Override
	public Long doCount(BaseParameter param) {
		if (param == null)
			return null;
		Criteria criteria = getSession().createCriteria(entityClass);
		processQuery(criteria, param);
		try {
			criteria.setProjection(Projections.rowCount());
			return ((Number) criteria.uniqueResult()).longValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<T> doQuery(BaseParameter param) {
		if (param == null)
			return null;
		Criteria criteria = getSession().createCriteria(entityClass);
		processQuery(criteria, param);
		try {
			if (param.getSortedConditions() != null && param.getSortedConditions().size() > 0) {
				Map<String, String> map = param.getSortedConditions();
				for (Iterator<String> it = param.getSortedConditions().keySet().iterator(); it.hasNext();) {
					String pm = it.next();
					if (BaseParameter.SORTED_DESC.equals(map.get(pm))) {
						criteria.addOrder(Order.desc(pm));
					} else if (BaseParameter.SORTED_ASC.equals(map.get(pm))) {
						criteria.addOrder(Order.asc(pm));
					}
				}
			}
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public QueryResult<T> doPaginationQuery(BaseParameter param) {
		return doPaginationQuery(param, true);
	}

	public QueryResult<T> doPaginationQuery(BaseParameter param, boolean bool) {
		if (param == null)
			return null;
		Criteria criteria = getSession().createCriteria(entityClass);

		if (bool)
			processQuery(criteria, param);
		else
			extendprocessQuery(criteria, param);

		try {
			QueryResult<T> qr = new QueryResult<T>();
			criteria.setProjection(Projections.rowCount());
			qr.setTotalCount(((Number) criteria.uniqueResult()).longValue());
			if (qr.getTotalCount() > 0) {
				if (param.getSortedConditions() != null && param.getSortedConditions().size() > 0) {
					Map<String, String> map = param.getSortedConditions();
					for (Iterator<String> it = param.getSortedConditions().keySet().iterator(); it.hasNext();) {
						String pm = it.next();
						if (BaseParameter.SORTED_DESC.equals(map.get(pm))) {
							criteria.addOrder(Order.desc(pm));
						} else if (BaseParameter.SORTED_ASC.equals(map.get(pm))) {
							criteria.addOrder(Order.asc(pm));
						}
					}
				}
				criteria.setProjection(null);
				criteria.setMaxResults(param.getMaxResults());
				criteria.setFirstResult(param.getFirstResult());
				qr.setResultList(criteria.list());
			} else {
				qr.setResultList(new ArrayList<T>());
			}
			return qr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	//***************************************************
	//以下为辅助方法
	//***************************************************
	private String getOpt(String value) {
		return (value.substring(0, value.indexOf('_', 1))).substring(1);
	}

	private String getPropName(String value) {
		return value.substring(value.indexOf('_', 1) + 1);
	}

	/** ************ for QBC ********** */
	private Method getMethod(String name) {
		if (!MAP_METHOD.containsKey(name)) {
			Class<Restrictions> clazz = Restrictions.class;
			Class[] paramType = new Class[] { String.class, Object.class };
			Class[] likeParamType = new Class[] { String.class, String.class, MatchMode.class };
			Class[] isNullType = new Class[] { String.class };
			try {
				Method method = null;
				if ("like".equals(name)) {
					method = clazz.getMethod(name, likeParamType);
				} else if ("isNull".equals(name)) {
					method = clazz.getMethod(name, isNullType);
				} else {
					method = clazz.getMethod(name, paramType);
				}
				MAP_METHOD.put(name, method);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return MAP_METHOD.get(name);
	}
	
	private Method getExtendMethod(String name) {
		if (!MAP_METHOD.containsKey(name)) {
			Class<Restrictions> clazz = Restrictions.class;
			Class[] paramType = new Class[] { String.class, Object.class };
			Class[] likeParamType = new Class[] { String.class, String.class, MatchMode.class };
			Class[] isNullType = new Class[] { String.class };
			// Class[] inparamType=new Class[]{String.class,Arrays.class};
			try {
				Method method = null;
				if ("like".equals(name)) {
					method = clazz.getMethod(name, likeParamType);
				} else if ("isNull".equals(name)) {
					method = clazz.getMethod(name, isNullType);
				} else if ("IN".equals(name.toUpperCase())) {
					// method=clazz.getMethod(name,inparamType);
				} else {
					method = clazz.getMethod(name, paramType);
				}
				MAP_METHOD.put(name, method);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return MAP_METHOD.get(name);
	}
	
	
	private void appendQL(StringBuffer sb, String[] propName, Object[] propValue) {
		for (int i = 0; i < propName.length; i++) {
			String name = propName[i];
			Object value = propValue[i];
			if (value instanceof Object[] || value instanceof Collection<?>) {
				Object[] arraySerializable = (Object[]) value;
				if (arraySerializable != null && arraySerializable.length > 0) {
					sb.append(" and o." + name + " in (:" + name.replace(".", "") + ")");
				}
			} else {
				if (value == null) {
					sb.append(" and o." + name + " is null ");
				} else {
					sb.append(" and o." + name + "=:" + name.replace(".", ""));
				}
			}
		}
	}
	
	private void setParameter(Query query, String[] propName, Object[] propValue) {
		for (int i = 0; i < propName.length; i++) {
			String name = propName[i];
			Object value = propValue[i];
			if (value != null) {
				if (value instanceof Object[]) {
					query.setParameterList(name.replace(".", ""), (Object[]) value);
				} else if (value instanceof Collection<?>) {
					query.setParameterList(name.replace(".", ""), (Collection<?>) value);
				} else {
					query.setParameter(name.replace(".", ""), value);
				}
			}
		}
	}
	
	private void processQuery(Criteria criteria, BaseParameter param) {
		try {
			Map<String, Object> staticConditionMap = core.util.BeanUtils.describeAvailableParameter(param);
			Map<String, Object> dynamicConditionMap = param.getQueryDynamicConditions();
			if ((staticConditionMap != null && staticConditionMap.size() > 0)) {
				for (Entry<String, Object> e : staticConditionMap.entrySet()) {
					Object value = e.getValue();
					if (value != null && !(value instanceof String && "".equals((String) value))) {
						String prop = getPropName(e.getKey());
						String methodName = getOpt(e.getKey());
						Method m = getMethod(methodName);
						if ("like".equals(methodName)) {
							criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop, value, MatchMode.ANYWHERE }));
						} else if ("isNull".equals(methodName) && value instanceof Boolean) {
							if ((Boolean) value) {
								criteria.add(Restrictions.isNull(prop));
							} else {
								criteria.add(Restrictions.isNotNull(prop));
							}
						} else {
							criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop, value }));
						}
					}
				}
			}
			if (dynamicConditionMap != null && dynamicConditionMap.size() > 0) {
				Object bean = entityClass.newInstance();
				Map<String, Object> map = new HashMap<String, Object>();
				for (Entry<String, Object> e : dynamicConditionMap.entrySet()) {
					map.put(getPropName(e.getKey()), e.getValue());
				}
				BeanUtils.populate(bean, map);
				for (Entry<String, Object> e : dynamicConditionMap.entrySet()) {
					String pn = e.getKey();
					String prop = getPropName(pn);
					String methodName = getOpt(pn);
					Method m = getMethod(methodName);
					Object value = PropertyUtils.getNestedProperty(bean, prop);

					if (value != null && !(value instanceof String && "".equals((String) value))) {
						if ("like".equals(methodName)) {
							criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop, value, MatchMode.ANYWHERE }));
						} else if ("isNull".equals(methodName) && value instanceof Boolean) {
							if ((Boolean) value) {
								criteria.add(Restrictions.isNull(prop));
							} else {
								criteria.add(Restrictions.isNotNull(prop));
							}
						} else {
							criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop, value }));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void extendprocessQuery(Criteria criteria, BaseParameter param) {
		try {
			Map<String, Object> staticConditionMap = core.util.BeanUtils.describeAvailableParameter(param);
			Map<String, Object> dynamicConditionMap = param.getQueryDynamicConditions();
			if ((staticConditionMap != null && staticConditionMap.size() > 0)) {
				for (Entry<String, Object> e : staticConditionMap.entrySet()) {
					Object value = e.getValue();
					if (value != null && !(value instanceof String && "".equals((String) value))) {
						String prop = getPropName(e.getKey());
						String methodName = getOpt(e.getKey());
						Method m = getExtendMethod(methodName);
						if ("like".equals(methodName)) {
							criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop, value, MatchMode.ANYWHERE }));
						} else if ("isNull".equals(methodName) && value instanceof Boolean) {
							if ((Boolean) value) {
								criteria.add(Restrictions.isNull(prop));
							} else {
								criteria.add(Restrictions.isNotNull(prop));
							}
						} else {
							if (value != null && value instanceof Object[] && "IN".equals(methodName.toUpperCase())) {
								Object[] obj = (Object[]) value;
								criteria.add(Restrictions.in(prop, obj));
								// criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop, obj }));
							} else {
								criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop, value }));
							}
						}
					}
				}
			}

			if (dynamicConditionMap != null && dynamicConditionMap.size() > 0) {
				Object bean = entityClass.newInstance();
				Map<String, Object> map = new HashMap<String, Object>();
				for (Entry<String, Object> e : dynamicConditionMap.entrySet()) {
					map.put(getPropName(e.getKey()), e.getValue());
				}
				BeanUtils.populate(bean, map);
				for (Entry<String, Object> e : dynamicConditionMap.entrySet()) {
					String pn = e.getKey();
					String prop = getPropName(pn);
					String methodName = getOpt(pn);
					Method m = getMethod(methodName);
					Object value = PropertyUtils.getNestedProperty(bean, prop);

					if (value != null && !(value instanceof String && "".equals((String) value))) {
						if ("like".equals(methodName)) {
							criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop, value, MatchMode.ANYWHERE }));
						} else if ("isNull".equals(methodName) && value instanceof Boolean) {
							if ((Boolean) value) {
								criteria.add(Restrictions.isNull(prop));
							} else {
								criteria.add(Restrictions.isNotNull(prop));
							}
						} else {
							criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop, value }));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
