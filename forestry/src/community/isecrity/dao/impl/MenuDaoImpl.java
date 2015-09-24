package community.isecrity.dao.impl;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hadoop.secrity.dao.MenuDao;
import com.hadoop.secrity.entity.Menu;
import com.hadoop.util.BaseConstant;

@Repository("menuDao")
public class MenuDaoImpl implements MenuDao{
	@Autowired
	public SqlSession sqlSession;
	
	@Override
	public int addMenu(Menu menu) {
		int i=sqlSession.insert(BaseConstant.SECRITY_MENU_SPACE+".addMenu",menu);
		if(i==1)
			i=menu.getId();
		return i;
	}
	
	@Override
	public Menu selectMenuByName(String name) {
		return sqlSession.selectOne(BaseConstant.SECRITY_MENU_SPACE+".selectMenuByName", name);
	}

	@Override
	public int delMenuById(Integer id) {
		return sqlSession.delete(BaseConstant.SECRITY_MENU_SPACE+".delMenuById", id);
	}

	@Override
	public int updateMenu(Menu menu) {
		return sqlSession.update(BaseConstant.SECRITY_MENU_SPACE+".updateMenu", menu);
	}

	@Override
	public Menu selectMenuById(Integer mId) {
		return sqlSession.selectOne(BaseConstant.SECRITY_MENU_SPACE+".selectMenuById", mId);
	}
	
	
	@Override
	public List<Menu> selectChildMenu(Integer pId){		
		return sqlSession.selectList(BaseConstant.SECRITY_MENU_SPACE+".selectChildMenu", pId);
	}

	@Override
	public List<Menu> selectAllPChildMenu(List<Integer> list) {
		return sqlSession.selectList(BaseConstant.SECRITY_MENU_SPACE+".selectAllPChildMenu", list);
	}
}