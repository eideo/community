package community.isecrity.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hadoop.secrity.dao.UserDao;
import com.hadoop.secrity.entity.User;
import com.hadoop.secrity.entity.UserRoleLink;
import com.hadoop.util.BaseConstant;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int addUser(User user) {
//		返回的i是影响的行数
		int i=sqlSession.insert(BaseConstant.SECRITY_USER_SPACE+".addUser", user);
		//通过刚插入的user对象,获取其id
		if(i==1)
			i=user.getId();
		return i;
	}

	@Override
	public int addUserRole(UserRoleLink userRoleLink) {
		return sqlSession.insert(BaseConstant.SECRITY_USER_SPACE+".addUserRole", userRoleLink);
	}

	@Override
	public int addUsersRolesBatch(List<UserRoleLink> list) {
		return sqlSession.insert(BaseConstant.SECRITY_USER_SPACE+".addUsersRolesBatch",list);
	}

	@Override
	public int delUserById(Integer id) {
		return sqlSession.delete(BaseConstant.SECRITY_USER_SPACE+".delUserById",id);
	}

	@Override
	public int delUserRoles(Integer id) {
		return sqlSession.delete(BaseConstant.SECRITY_USER_SPACE+".delUserRoles",id);
	}
	
	
	@Override
	@SuppressWarnings("rawtypes")
	public int delUserRolesBatch(Map paramMap){
		return sqlSession.delete(BaseConstant.SECRITY_USER_SPACE+".delUserRolesBatch",paramMap);
	}
	

	@Override
	public int updateUser(User user) {
		return sqlSession.update(BaseConstant.SECRITY_USER_SPACE+".updateUser", user);
	}


	@Override
	public User selectUserById(Integer uId) {
		return sqlSession.selectOne(BaseConstant.SECRITY_USER_SPACE+".selectUserById",uId);
	}
	
	@Override
	public User selectUserAllById(Integer uId) {
		return sqlSession.selectOne(BaseConstant.SECRITY_USER_SPACE+".selectUserSomeById",uId);
	}


	@Override
	public User selectUserRoleById(Integer uId) {
		return sqlSession.selectOne(BaseConstant.SECRITY_USER_SPACE+".selectUserRolesById",uId);
	}
	
	@Override
	public User selectUserByAccount(User user) {
		return sqlSession.selectOne(BaseConstant.SECRITY_USER_SPACE+".selectUserByAccount",user);
	}
	
	@Override
	public Page<User> selectUsersByConditon4Pages(Page<User> page,User user) {
		List<User> list=null;	
        PageHelper.startPage(page.getPageNum(),page.getPageSize(),true);
        list = sqlSession.selectList(BaseConstant.SECRITY_USER_SPACE+".selectUsersByConditon4Pages",user);
        page=(Page<User>) list;
        return page;
        
        
        
//        以下为测试,勿删
//        for (User user2 : (Page<User>) list) {
//        	System.out.println(user2.getId());
//        }
//        System.out.println(((Page) list).getPages()+"总页数");
//        assertEquals(10, list.size());
//        assertEquals(183, ((Page) list).getTotal());
	}

	@Override
	public int selectUserCountsByCondtion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserRoleLink> selectUserRoleLinksById(Integer Id) {
		User user = new User();
		user.setId(Id);
		return sqlSession.selectList(BaseConstant.SECRITY_USER_SPACE+".selectUserRoleLinksById",user);
	}
}
