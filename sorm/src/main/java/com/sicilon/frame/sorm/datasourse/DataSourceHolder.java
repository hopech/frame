package com.sicilon.frame.sorm.datasourse;



import java.util.ArrayList;
import java.util.List;

public final class DataSourceHolder {
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();
	public static List<String> dataSourceIds = new ArrayList<String>();


    private DataSourceHolder() {
        //
    }

    public static void putDataSource(String dataSource){
        holder.set(dataSource);
    }

    public static String getDataSource(){
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }
}
