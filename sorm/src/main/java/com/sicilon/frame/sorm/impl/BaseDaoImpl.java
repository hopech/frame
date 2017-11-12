package com.sicilon.frame.sorm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.sicilon.frame.sorm.BaseDao;
import com.sicilon.frame.sorm.BaseEntity;
import com.sicilon.frame.sorm.myjdbc.MyJdbcImpl;
import com.sicilon.frame.sorm.myjdbc.ReadWriteSeparation;
import com.sicilon.frame.sorm.utils.ConvertUtil;
import com.sicilon.frame.sorm.utils.PropertiesUtil;

/**
 * baseDao实现类
 * @author SmallFour
 * @date 创建时间：2017年5月5日 下午3:44:15
 * @version 1.0 
 */
@Repository
public class BaseDaoImpl implements BaseDao{
	
	@Autowired
    private MyJdbcImpl myJdbcImpl;
	
	private static boolean WRITEREADSWITCH = false;
	static{
		try {
			WRITEREADSWITCH = ConvertUtil.stringToBoolean(
					PropertiesUtil.getClasspathPropValue("application.properties", "write_read_switch"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 
	 * 添加
	 */
	@Override
	public int insert(BaseEntity<?> bean) {
		try {
			String insertSql = SqlUtils.buildInsertSql(bean.getClass());//生成sql
			SqlParameterSource sps = new BeanPropertySqlParameterSource(bean);//允许 bean 或者 map 进行赋值
			return myJdbcImpl.insert(insertSql, sps);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 添加返回自增主键
	 */
	@Override  
	public Long insertReturPK(BaseEntity<?> bean) {
		try {
			String insertSql = SqlUtils.buildInsertSql(bean.getClass());//生成sql
			SqlParameterSource sps = new BeanPropertySqlParameterSource(bean);//允许 bean 或者 map 进行赋值
			return myJdbcImpl.insertReturPK(insertSql, sps);//返回主键
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 修改
	 */
	@Override
	public int update(BaseEntity<?> bean) {
		try {
			String sql = SqlUtils.buildUpdateSql(bean.getClass());//生成sql
	        SqlParameterSource sps = new BeanPropertySqlParameterSource(bean);//允许 bean 或者 map 进行赋值
			return myJdbcImpl.update(sql, sps);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 删除
	 */
	@Override
	public int delete(Class<?> cs, Long nid) {
		try {
			String sql = SqlUtils.buildDeleteSql(cs);//生成sql语句
			String  substring = sql.substring(0, sql.indexOf(":"))+nid;
			//String substring = sql.replace(":id", ""+nid);
			return myJdbcImpl.delete(substring);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 查询返回list集合
	 */
	@Override
	public <T> List<T> queryList(Class<T> cs, String sql, Object ...objects) {
		try {
			if(WRITEREADSWITCH){
				ReadWriteSeparation.read();													  //路由到读的从库
			}
			Map<String, Object> map = new HashMap<String, Object>();
			return SqlUtils.coverListToBean(myJdbcImpl.queryList(sql, map, objects), cs);//map转bean
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 查询单条数据 
	 */
	@Override
	public <T> T queryEntity(Class<T> cs, String sql, Object ...objects) {
		try {
			if(WRITEREADSWITCH){
				ReadWriteSeparation.read();							//路由到读的从库
			}
			Map<String, Object> queryForMap = myJdbcImpl.queryMap(sql, objects);
			if(queryForMap != null){
				return SqlUtils.coverMapToModel(queryForMap, cs);//map转bean
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 查询返回count
	 */
	@Override
	public Long count(String sql, Object ...objects) {
		try {
			return myJdbcImpl.queryObject(sql, objects);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 查询返回list<map>
	 */
	@Override
	public List<Map<String, Object>> queryMap(String sql, Object ...objects) {
		try {
			if(WRITEREADSWITCH){
				ReadWriteSeparation.read();	 //路由到读的从库
			}
			return myJdbcImpl.queryList(sql, objects);//查询得到map
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 查询返回pageList
	 */
	@Override
	public <T> PageList<T> queryListResult(Class<T> cs, String sql, Integer page, Integer pageSize, Object ...objects) {
		try {
			if(WRITEREADSWITCH){
				ReadWriteSeparation.read();												//路由到读的从库
			}
			String queryListSql = sql+SqlUtils.getLimitSql(page,pageSize);;
			PageList<T> pageList =  new PageList<T>();
			pageList.setList((List<T>) SqlUtils.coverListToBean(myJdbcImpl.queryList(queryListSql, objects), cs));
			return pageList;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 执行sql  进行 写操作 返回int
	 */
	@Override
	public int execute(String sql, Object ...objects) {
		try {
			return myJdbcImpl.update(sql, objects);//sql修改
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 执行sql语句返回首行首列
	 */
	@Override
	public String executeNonQuery(String sql, Object ...objects) {
		try {
			List<Map<String, Object>> queryList = myJdbcImpl.queryList(sql, objects);
			@SuppressWarnings("unchecked")
			Entry<String, Object> array = (Entry<String, Object>) queryList.get(0).entrySet().toArray()[0];
			return String.valueOf(array.getValue());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 查询 根据int 或者 Integer id 返回一个entity实体
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T>T loadEntity(Class<? extends BaseEntity<T>> cs, Long id) {
		try {
			if(WRITEREADSWITCH){
				ReadWriteSeparation.read();													//路由到读的从库
			}
			String buildSelectSql = SqlUtils.buildSelectSql(cs)+Condition.selectWhereById(cs, id);//SELECT id, name, sex, gaga FROM t_wangyang
			return  (T) SqlUtils.coverMapToBean(myJdbcImpl.queryMap(buildSelectSql), cs);//查询返回map//map转bean
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* 
	 * 查询 根据String id 返回一个entity实体
	 */
	@SuppressWarnings("unchecked")
	public <T>T loadEntity(Class<? extends BaseEntity<T>> cs, String id) {
		try {
			if(WRITEREADSWITCH){
				ReadWriteSeparation.read();													 //路由到读的从库
			}
			String buildSelectSql = SqlUtils.buildSelectSql(cs)+Condition.selectWhereById(cs, id);//生成sql
			return (T) SqlUtils.coverMapToBean(myJdbcImpl.queryMap(buildSelectSql), cs);//查询返回map//map转bean
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} 
	}

	/* 
	 * 批量添加 list内不可大于3000条，每次
	 */
	@Override
	public int insertBath(List<?> list) {
		try {
			if(list.size()>3000){
					throw new Exception("insert Quantity greater than 3000 !!");
			}else{
				String insertSql = SqlUtils.buildInsertBathSql(list);//生成sql
				return myJdbcImpl.update(insertSql);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public <T> List<T> queryPageList(Class<T> cs, String sql, Integer page, Integer pageSize, Object ...objects) {
		try {
			if(WRITEREADSWITCH){
				ReadWriteSeparation.read();												//路由到读的从库
			}
			String queryListSql = sql+SqlUtils.getLimitSql(page,pageSize);
			return SqlUtils.coverListToBean(myJdbcImpl.queryList(queryListSql, objects), cs);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
