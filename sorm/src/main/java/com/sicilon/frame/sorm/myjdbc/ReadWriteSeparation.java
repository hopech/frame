package com.sicilon.frame.sorm.myjdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sicilon.frame.sorm.datasourse.DataSourceHolder;
import com.sicilon.frame.sorm.loadbalancing.WeightAlgorithm;

//读写切换
public class ReadWriteSeparation {
	
	private final static Logger logger = LoggerFactory.getLogger(ReadWriteSeparation.class);
	
	public static void read(){
		String masterDataSource = DataSourceHolder.getDataSource();
    	//if(StringUtil.isNotBlank(masterDataSource)){
    		String server = WeightAlgorithm.getServer(masterDataSource);
    		logger.info("当前选用的从数据源>>>>>>"+server);
    		DataSourceHolder.putDataSource(server);
    	//}
	}
	
	public static void write(){
		
	}
}
