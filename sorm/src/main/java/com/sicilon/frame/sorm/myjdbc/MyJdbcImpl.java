package com.sicilon.frame.sorm.myjdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * jdbcTemplate 封装 ，封装常用数据库操作 
 * @author SmallFour
 * @date 创建时间：2017年5月5日 下午3:44:54
 * @version 1.0 
 */
@Repository
public class MyJdbcImpl{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    /**
     * 添加
     * @param insertSql 
     * 				执行的sql
     * @param sps 
     * 				映射sql上的值
     * @return
     */
    public int insert(String insertSql, SqlParameterSource sps){
    	return namedParameterJdbcTemplate.update(insertSql, sps);
    }
    
    /**
     * 添加  返回主键
     * @param sql 
     * 				执行的sql
     * @param sps
     * 				映射sql上的值
     * @return
     */
    public Long insertReturPK(String sql, SqlParameterSource sps) {
    	KeyHolder keyHolder = new GeneratedKeyHolder();
    	return insertReturPK(sql, sps, keyHolder);
    }
    
    /**
     * 添加  返回主键
     * @param sql
     * 				执行的sql
     * @param sps
     * 				映射sql上的值
     * @param keyHolder
     * 				获取返回的主键
     * @return
     */
    public Long insertReturPK(String sql, SqlParameterSource sps, KeyHolder keyHolder) {
    	namedParameterJdbcTemplate.update(sql, sps, keyHolder);
    	return keyHolder.getKey().longValue();
    }
    
    /**
     * 修改
     * @param sql
     * 				执行的sql
     * @param sps
     * 				映射sql上的值
     * @return
     */
    public int update(String sql, SqlParameterSource sps) {
    	return namedParameterJdbcTemplate.update(sql, sps);
    }

    /**
     * 删除
     * @param sql
     * 				执行删除的sql
     * @return
     */
    public int delete(String sql) {
    	return jdbcTemplate.update(sql);
    }
    
    /**
     * 根据sql进行查询
     * @param sql
     * 				执行查询的sql
     * @return
     */
    public List<Map<String, Object>> queryList(String sql, Object ...objects) {
		Map<String, Object> map = new HashMap<String, Object>();
		return queryList(sql, map, objects);
	}
    
    /**
     * 根据sql进行查询
     * @param sql
     * 				执行查询的sql
     * @param map
     * 				map集合
     * @return
     */
    public List<Map<String, Object>> queryList(String sql, Map<String, Object> map, Object ...objects) {
    	List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql, objects);
    	//return namedParameterJdbcTemplate.queryForList(sql, map);
    	return queryForList;
    }
    
    /**
     * sql查询返回map
     * @param sql
     * 				执行查询的sql
     * @return
     */
    public Map<String, Object> queryMap(String sql, Object ...objects) {
    	List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, objects);
    	if(!resultList.isEmpty()){
    		return resultList.get(0);
    	}
		return null;
    }
    
    /**
     * @param sql
     * @return
     */
	public Long queryObject(String sql, Object ...objects) {
    	return jdbcTemplate.queryForObject(sql, objects, Long.class);
    }
    
    /**
     * 单执行写操作sql
     * @param sql
     * 				将要执行的sql操作
     * @return
     */
    public int update(String sql, Object ...objects) {
    	return jdbcTemplate.update(sql, objects);
    }
}