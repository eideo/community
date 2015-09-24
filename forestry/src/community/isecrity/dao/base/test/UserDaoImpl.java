package community.isecrity.dao.base.test;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import community.isecrity.dao.base.factory.BaseFactoryImpl;

@Repository("userDao")
public class UserDaoImpl<User> extends BaseFactoryImpl<User> implements UserDao<User>{	
	@Autowired
	SessionFactory sessionFactory;
	
	public UserDaoImpl(){
		//this.path="";
		setSF(sessionFactory);
	}
	


}
