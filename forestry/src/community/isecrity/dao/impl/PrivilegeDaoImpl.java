package community.isecrity.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hadoop.secrity.dao.PrivilegeDao;
import com.hadoop.secrity.entity.Privilege;
import com.hadoop.util.BaseConstant;

@Repository("privilegeDao")
public class PrivilegeDaoImpl implements PrivilegeDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int addPrivilege(Privilege privilege) {
		int i=sqlSession.insert(BaseConstant.SECRITY_PRIV_SPACE+".addPrivilege",privilege);
		if(i>0)
			i=privilege.getId();
		return i;
	}

	@Override
	public int delPrivilegeById(Integer id) {
		int i = sqlSession.delete(BaseConstant.SECRITY_PRIV_SPACE+".delPrivilegeById", id);
		return i;
	}
	
	@Override
	public int delPrivilegesByMenuId(Integer mId) {
		int i = sqlSession.delete(BaseConstant.SECRITY_PRIV_SPACE+".delPrivilegesByMenuId", mId);
		return i;
	}


	@Override
	public int updatePrivilege(Privilege privilege) {
		int i = sqlSession.update(BaseConstant.SECRITY_PRIV_SPACE+".updatePrivilege", privilege);
		return i;
	}
	


	@Override
	public Privilege selectPrivById(Integer pId) {
		return sqlSession.selectOne(BaseConstant.SECRITY_PRIV_SPACE+".selectPrivById",pId);
	}

	@Override
	public Privilege selectPrivMenusById(Integer id) {
		return sqlSession.selectOne(BaseConstant.SECRITY_PRIV_SPACE+".selectPrivById", id);
	}

	@Override
	public List<Privilege> selectAllPrivileges() {
		return sqlSession.selectList(BaseConstant.SECRITY_PRIV_SPACE+".selectAllPrivileges");
	}

	@Override
	public List<Privilege> selectAllMenuPrivileges() {
		return sqlSession.selectList(BaseConstant.SECRITY_PRIV_SPACE+".selectAllMenuPrivileges");
	}

	@Override
	public List<Privilege> selectAllSubMenuPrivileges(Privilege privilege) {
		return sqlSession.selectList(BaseConstant.SECRITY_PRIV_SPACE+".selectAllSubMenuPrivileges",privilege);
	}
}
