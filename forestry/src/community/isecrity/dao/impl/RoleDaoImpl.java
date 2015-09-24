package community.isecrity.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hadoop.secrity.dao.RoleDao;
import com.hadoop.secrity.entity.Role;
import com.hadoop.secrity.entity.RolePrivilegeLink;
import com.hadoop.secrity.entity.User;
import com.hadoop.system.entity.LoginLog;
import com.hadoop.util.BaseConstant;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int addRole(Role role) {
		int i=sqlSession.insert(BaseConstant.SECRITY_ROLE_SPACE+".addRole", role);
		if(i==1)
			i=role.getId();
		return i;
	}

	@Override
	public int addRolePrivRelation(RolePrivilegeLink rolePrivilegeLink) {
		return sqlSession.insert(BaseConstant.SECRITY_ROLE_SPACE+".addRolePrivRelation", rolePrivilegeLink);
	}


	@Override
	public int addRolePrivsRelation(List<RolePrivilegeLink> list) {
		return sqlSession.insert(BaseConstant.SECRITY_ROLE_SPACE+".addRolePrivsRelation",list);
	}

	@Override
	public int delRoleById(int id) {
		return sqlSession.delete(BaseConstant.SECRITY_ROLE_SPACE+".delRoleById", id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int delRolePrivsBatch(Map paramMap) {
		return sqlSession.delete(BaseConstant.SECRITY_ROLE_SPACE+".delRolePrivsBatch", paramMap);
	}
	
	@Override
	public int delRolePrivsByRoleId(int id) {
		return sqlSession.delete(BaseConstant.SECRITY_ROLE_SPACE+".delRolePrivsByRoleId", id);
	}

	@Override
	public int delRolePrivsOneStep(int id) {
		return sqlSession.delete(BaseConstant.SECRITY_ROLE_SPACE+".delRolePrivsOneStep", id);
	}

	@Override
	public int updateRole(Role role) {
		return sqlSession.update(BaseConstant.SECRITY_ROLE_SPACE+".updateRole", role);
	}
	
	@Override
	public Role selectRoleById(Integer id){
		return sqlSession.selectOne(BaseConstant.SECRITY_ROLE_SPACE+".selectRoleById", id);
	}

	@Override
	public List<Role> selectRoleList() {
		return sqlSession.selectList(BaseConstant.SECRITY_ROLE_SPACE+".selectRoleList");
	}

	@Override
	public Role selectRolePrivsById(Integer id) {
		return sqlSession.selectOne(BaseConstant.SECRITY_ROLE_SPACE+".selectRolePrivsById",id);
	}
	
	@Override
	public Page<Role> selectRolesByCondtion(Page<Role> page, Role role) {	
		List<Role> list=null;	
        PageHelper.startPage(page.getPageNum(),page.getPageSize(),true);
        list = sqlSession.selectList(BaseConstant.SECRITY_ROLE_SPACE+".selectRolesByCondtion",role);
        page=(Page<Role>) list;
        return page;	
	}

	@Override
	public Page<Role> selectRoles(Page<Role> page) {
		List<Role> list = null;	
        PageHelper.startPage(page.getPageNum(),page.getPageSize(),true);
        list = sqlSession.selectList(BaseConstant.SYS_LOGINLOG_SPACE+".selectRoleList");
        page = (Page<Role>) list;
        return page;
	}
}
