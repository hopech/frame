package com.sicilon.frame.sorm;

import java.util.List;
import java.util.Map;

import com.sicilon.frame.sorm.impl.PageList;

/**
 * @author SmallFour
 * @date 创建时间：2017年5月5日 下午3:44:06
 * @version 1.0 
 */
public interface BaseDao {

	/**
	 * 保存新增的实体对象
	 * 
	 * @param bean entity
	 * @return
	 */
	int insert(BaseEntity<?> bean);

	/**
	 * 保存新增的实体对象
	 * 
	 * @param bean entity
	 * @return
	 */
	Long insertReturPK(BaseEntity<?> bean);
	
	/**
	 * 批量添加
	 * @param list  entity  
	 * 				最大3000条    添加entity类里不能有自增主键
	 * @return
	 * @throws Exception
	 */
	int insertBath(List<?> list);

	/**
	 * 根据主键保存修改的实体对象
	 * 
	 * @param bean entity
	 * @return
	 */
	int update(BaseEntity<?> bean);

	/**
	 * 根据bean的pk来删除bean
	 * @param cs 
	 * 			entity.class
	 * @param nid 
	 * 			删除id
	 * @return
	 * @throws Exception
	 */
	int delete(Class<?> cs, Long nid);


	/**
	 * 返回list
	 * @param cs
	 * 			entity.class
	 * @param sql
	 * 			执行sql语句
	 * @return
	 * @throws Exception
	 */
	<T> List<T> queryList(Class<T> cs, String sql, Object ...objects);

	/**
	 * 查询满足条件的单条记录的实体对象，如果超过1条则抛出异常，没查询到则返回null
	 * @param cs
	 * 			entity类
	 * @param sql
	 * 			执行sql语句
	 * @return
	 * @throws Exception
	 */
	<T> T queryEntity(Class<T> cs, String sql, Object ...objects);

	/**
	 * 查询count
	 * @param cs
	 * 			entity类
	 * @param conditionSql
	 * 			条件sql
	 * @return
	 */
	Long count(String sql, Object ...objects);

	/**
	 * 传递sql返回map
	 * 
	 * @param sql
	 * 			执行sql语句
	 * @return
	 */
	List<Map<String, Object>> queryMap(String sql, Object ...objects);
	

	
	
	/**
	 * 根据id查询单条数据
	 * @param cs 
	 * 			model类
	 * @param id 
	 * 			Integer 类型
	 * @return
	 */
	<T>T loadEntity(Class<? extends BaseEntity<T>> cs, Long id);
	
	/**
	 * 根据id查询单条数据
	 * @param cs 
	 * 			model类
	 * @param id 
	 * 			String 类型
	 * @return
	 */
	<T>T loadEntity(Class<? extends BaseEntity<T>> cs, String id);

	
	
	/**
	 * 返回pagelist分页工具类
	 * 
	 * @param cs
	 *            model类
	 * @param thisPage
	 *            当前页
	 * @param pageSize
	 *            每页总条数
	 * @param sql
	 *            查询sql
	 * @return
	 */
	<T> List<T> queryPageList(Class<T> cs, String sql, Integer page, Integer PageSize, Object ...objects);
	/**
	 * 返回pagelist分页工具类
	 * 
	 * @param cs
	 *            model类
	 * @param thisPage
	 *            当前页
	 * @param pageSize
	 *            每页总条数
	 * @param sql
	 *            查询sql
	 * @return
	 */
	<T> PageList<T> queryListResult(Class<T> cs, String sql, Integer page, Integer PageSize, Object ...objects);
	
	
	/////////////////////////////////////////////////////////////////////////////////
	/**
	 * 执行sql语句返回受影响行数
	 * 
	 * @param sql
	 * 			执行写入操作的语句
	 * @return
	 * @throws Exception
	 */
	int execute(String sql, Object ...objects);

	/**
	 * 执行sql语句返回首行首列
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	String executeNonQuery(String sql, Object ...objects);
}
