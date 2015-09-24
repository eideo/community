package community.isecrity.dao.base;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.dbutils.RowProcessor;
import com.cplatform.dbhelp.page.ListPage;
import com.cplatform.dbhelp.page.Page;
import community.core.iAnno.GetAnno;

/**
 * @CopyRight：http://www.netrust.cn/
 *
 * @Description: 使用jdbc公用时,请注意一下pojo与table表名与字段的对应关系  
 * @Author: lazite 
 * @CreateTime: 2015年9月23日 上午9:22:12   
 * @ModifyBy: lazite 
 * @ModeifyTime: 2015年9月23日 上午9:22:12   
 * @ModifyDescription:
 * @Version:   V1.0
 */
public class BaseDaoImpl_Jdbc<T> implements BaseDao_Jdbc<T> {
	@Autowired
	private DbHelper dbHelper;
		
	@Override
	public void add(T obj) {
		String sql="INSETER INTO ";
		StringBuffer sb=new StringBuffer(sql);
		sb.append(GetAnno.getClassAnno(obj.getClass()).get(0).getName());
		sb.append("VALUES");
		
		try {
			dbHelper.batch(null, null);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public int addBackId(T obj) {
		// TODO Auto-generated method stub
		String
		return 0;
	}

	@Override
	public void addBatch(List<T> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteByT(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(T obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBatch(List<T> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T load(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getBean(T t) {
		// TODO Auto-generated method stub
		dbHelper.BEAN_ROW_PROCESSOR.toBean(null,obj.getClass());
		dbHelper.
		return null;
	}

	@Override
	public List<T> getBeanList(T t) {
		
		try {
			return (List<T>) dbHelper.getBeanList(null, t.getClass(), null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getT4Page(Object obj, T t) {
		try {
			ListPage<T> list=dbHelper.getPage(null, null, 0, 0, null);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getTNums(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
