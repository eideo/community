package community.isecrity.dao.base.factory;

import community.isecrity.dao.base.BaseDao;
import community.isecrity.dao.base.BaseDaoImpl_Hibernate;
import community.isecrity.dao.base.BaseDaoImpl_Jdbc;
import community.isecrity.dao.base.BaseDaoImpl_Mybatis;

public abstract class BaseFactoryImpl<T> extends BaseDaoImpl_Hibernate<T>{
	
	public BaseDao<T> createBaseDao(int i){
		switch (i) {
			case 0:
				return new BaseDaoImpl_Hibernate<T>();
			case 1:
				return new BaseDaoImpl_Mybatis<T>();
			case 2:
				return new BaseDaoImpl_Jdbc<T>();
			default:
				return null;
		}
	}
	
}
