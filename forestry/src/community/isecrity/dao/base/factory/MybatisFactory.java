package community.isecrity.dao.base.factory;

import community.isecrity.dao.base.BaseDao;
import community.isecrity.dao.base.BaseDaoImpl_Mybatis;

public abstract class MybatisFactory<T> extends BaseDaoImpl_Mybatis<T> implements BaseFactory<T>{
	@Override
	public BaseDao<T> BaseFactory(int i) {
		return new BaseDaoImpl_Mybatis<>();
	}

}
