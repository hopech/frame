package com.sicilon.frame.sorm.datasourse;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Administrator
 *
 */
public class MyDataSource extends AbstractRoutingDataSource implements EnvironmentAware{

	private DataSource writeDataSource; //写数据源
	private Map<Object, Object> readDataSource = new HashMap<Object, Object>();
    
    private ConversionService conversionService = new DefaultConversionService();
	private PropertyValues dataSourcePropertyValues;
	
	// 如配置文件中未指定数据源类型，使用该默认值
	private static final Object DATASOURCE_TYPE_DEFAULT = "com.alibaba.druid.pool.DruidDataSource";

    @Override
    public void afterPropertiesSet() {
        if (this.writeDataSource == null) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        setDefaultTargetDataSource(writeDataSource);
        if(readDataSource != null) {
        	setTargetDataSources(readDataSource);
        }
        for (Object key : readDataSource.keySet()) {
			DataSourceHolder.dataSourceIds.add((String) key);
		}
        super.afterPropertiesSet();
    }
    
	@Override
	protected Object determineCurrentLookupKey() {
		String dynamicDataSourceGlobal = DataSourceHolder.getDataSource();
        return dynamicDataSourceGlobal;
	}

	public DataSource getWriteDataSource() {
		return writeDataSource;
	}

	public void setWriteDataSource(MyDataSource writeDataSource) {
		this.writeDataSource = writeDataSource;
	}


	public Map<Object, Object> getReadDataSource() {
		return readDataSource;
	}

	public void setReadDataSource(Map<Object, Object> readDataSource) {
		this.readDataSource = readDataSource;
	}

	@Override
	public void setEnvironment(Environment environment) {
		initDefaultDataSource(environment);
		initCustomDataSources(environment);
	}
	/**
	 * 创建DataSource
	 *
	 * @param type
	 * @param driverClassName
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 * @author SHANHY
	 * @create 2016年1月24日
	 */
	@SuppressWarnings("unchecked")
	public DataSource buildDataSource(Map<String, Object> dsMap) {
		try {
			Object type = dsMap.get("type");
			if (type == null)
				type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource

			Class<? extends MyDataSource> dataSourceType;
			dataSourceType = (Class<? extends MyDataSource>) Class.forName((String) type);

			String driverClassName = dsMap.get("driver-class-name").toString();
			String url = dsMap.get("url").toString();
			String username = dsMap.get("username").toString();
			String password = dsMap.get("password").toString();

			DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
					.username(username).password(password).type(dataSourceType);
			return factory.build();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 初始化主数据源
	 *
	 * @author SHANHY
	 * @create 2016年1月24日
	 */
	private void initDefaultDataSource(Environment env) {
		// 读取主数据源
		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
		Map<String, Object> dsMap = new HashMap<String, Object>();
		//读取连接池
		dsMap.put("type", propertyResolver.getProperty("type"));
		//驱动
		dsMap.put("driver-class-name", propertyResolver.getProperty("driver-class-name"));
		dsMap.put("url", propertyResolver.getProperty("url"));
		dsMap.put("username", propertyResolver.getProperty("username"));
		dsMap.put("password", propertyResolver.getProperty("password"));

		writeDataSource = buildDataSource(dsMap);
		dataBinder(writeDataSource, env);
	}
	/**
	 * 为DataSource绑定更多数据
	 *
	 * @param writeDataSource2
	 * @param env
	 * @author SHANHY
	 * @create 2016年1月25日
	 */
	private void dataBinder(Object writeDataSource2, Environment env) {
		RelaxedDataBinder dataBinder = new RelaxedDataBinder(writeDataSource2);
		dataBinder.setConversionService(conversionService);
		dataBinder.setIgnoreNestedProperties(false);// false
		dataBinder.setIgnoreInvalidFields(false);// false
		dataBinder.setIgnoreUnknownFields(true);// true
		if (dataSourcePropertyValues == null) {
			Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
			Map<String, Object> values = new HashMap<String, Object>(rpr);
			// 排除已经设置的属性
			values.remove("type");
			values.remove("driver-class-name");
			values.remove("url");
			values.remove("username");
			values.remove("password");
			dataSourcePropertyValues = new MutablePropertyValues(values);
		}
		dataBinder.bind(dataSourcePropertyValues);
	}

	/**
	 * 初始化更多数据源
	 *
	 * @author SHANHY
	 * @create 2016年1月24日
	 */
	private void initCustomDataSources(Environment env) {
		// 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");
		String dsPrefixs = propertyResolver.getProperty("names");
		if(dsPrefixs==null){
		}else{
			for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
				Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
				DataSource ds = buildDataSource(dsMap);
				readDataSource.put(dsPrefix, ds);
				dataBinder(ds, env);
			}
		}
	}
}
