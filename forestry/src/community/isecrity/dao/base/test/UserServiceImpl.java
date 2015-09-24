package community.isecrity.dao.base.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import community.isecrity.dao.base.support.BaseParameter;
import community.isecrity.entity.User;


@Transactional
@Service("userService")
public class UserServiceImpl {
	@Autowired
	UserDao<User> userDao;
	
	public void get(){
		userDao.add(new User());
		userDao.doPaginationQuery(new BaseParameter());
	}
}
