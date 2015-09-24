package community.isecrity.dao.base;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public class BaseDaoImpl_Mybatis<T> implements BaseDao_Mybatis<T> {
	@Autowired
	SqlSession sqlSession;
	protected String path = "";
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	/////////////////////////////////////////////////////
	//以下为继承baseDao的实现
	/////////////////////////////////////////////////////
	@Override
	public void add(T obj) {
		sqlSession.insert(path+".add", obj);
	}
	
	@Override
	public int addBackId(T obj) {
		int i=sqlSession.insert(path+".addBackId", obj);
		if(i==1)
			i=reflectId(obj);
		return i;
	}
	
	@Override
	public void addBatch(List<T> list) {
		sqlSession.insert(path+".addBatch", list);
	}
	
	@Override
	public void delete(Object obj) {
		sqlSession.delete(path+".delete", obj);
	}
	
	@Override
	public void deleteByT(T t) {
		sqlSession.delete(path+".deleteByT", t);
	}
	
	@Override
	public void update(T obj) {
		sqlSession.update(path+".update", obj);
	}
	
	@Override
	public void updateBatch(List<T> list) {
		sqlSession.update(path+".updateBatch", list);		
	}
	
	@Override
	public T load(Object obj) {
		return sqlSession.selectOne(path+".load", obj);
	}
	
	@Override
	public T getBean(T t) {
		return sqlSession.selectOne(path+".getBean", t);
	}
	
	@Override
	public List<T> getBeanList(T t) {
		return sqlSession.selectList(path+".getBeanList", t);
	}
	
	@Override
	public Object getT4Page(Object obj, T t) {
		@SuppressWarnings("unchecked")
		Page<T> page=(Page<T>)obj;
		List<T> list=null;	
		PageHelper.startPage(page.getPageNum(),page.getPageSize(),true);
		list = sqlSession.selectList(path+".getT4Page",obj);
		page=(Page<T>) list;
		
		return page;
	}
	
	@Override
	public long getTNums(T t) {
		return (long)sqlSession.selectOne(path+".getTNums", t);
	}
	
	/////////////////////////////////////////////////////
	//以上为继承baseDao的实现
	/////////////////////////////////////////////////////
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> int reflectId(T obj) { 
		Class cla=obj.getClass();
		Method method;
		try {
			method = cla.getMethod("getId",new Class[]{});
			return (Integer) method.invoke(obj,new Object[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
