package com.sicilon.frame.sorm.loadbalancing;

import java.util.List;

import com.sicilon.frame.sorm.datasourse.DataSourceHolder;

public class WeightAlgorithm {
	private static Integer pos=0;

	public static String getServer(String dataSource)
    {
		// 取得Ip地址List
    	List<String> dataSourceIds = DataSourceHolder.dataSourceIds;
        String server = null;
        synchronized (pos)
        {
            if (pos >= dataSourceIds.size())
                pos = 0;
            server = dataSourceIds.get(pos);
            pos ++;
        }
        System.out.println("pos>>>"+pos);
        return server;
    }
}
