package com.sicilon.frame.sweb.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期时间类
 * 
 * <p>
 * 对Calendar的封装,以便于使用
 * </p>
 * 
 * @author qsyang
 */
@SuppressWarnings("serial")
public class DateTimeUtil implements Serializable {

	/**
	 * yyyy-MM-dd HH:mm:ss 格式
	 */
	public static final String DEFAULT_DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd HH:mm 格式
	 */
	public static final String DEFAULT_DATE_TIME_HHmm_FORMAT_PATTERN = "yyyy-MM-dd HH:mm";
	/**
	 * yyyy-MM-dd HH 格式
	 */
	public static final String DEFAULT_DATE_TIME_HH_FORMAT_PATTERN = "yyyy-MM-dd HH";
	/**
	 * yyyy-MM-dd 格式
	 */
	public static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd";
	/**
	 * HH:mm:ss 格式
	 */
	public static final String DEFAULT_TIME_FORMAT_PATTERN = "HH:mm:ss";
	/**
	 * HH:mm 格式
	 */
	public static final String DEFAULT_TIME_HHmm_FORMAT_PATTERN = "HH:mm";
	/**
	 * 年
	 * <p>
	 * 可以通过MyDateTime.now().get(MyDateTime.YEAR_FIELD)来获取当前时间的年
	 * </p>
	 */
	public static final int YEAR_FIELD = java.util.Calendar.YEAR;
	/**
	 * 月
	 * <p>
	 * 可以通过MyDateTime.now().get(MyDateTime.MONTH_FIELD)来获取当前时间的月
	 * </p>
	 */
	public static final int MONTH_FIELD = java.util.Calendar.MONTH;
	/**
	 * 日
	 * <p>
	 * 可以通过MyDateTime.now().get(MyDateTime.DAY_FIELD)来获取当前时间的日
	 * </p>
	 */
	public static final int DAY_FIELD = java.util.Calendar.DATE;
	/**
	 * 小时
	 * <p>
	 * 可以通过MyDateTime.now().get(MyDateTime.HOUR_FIELD)来获取当前时间的小时
	 * </p>
	 */
	public static final int HOUR_FIELD = java.util.Calendar.HOUR_OF_DAY;
	/**
	 * 分钟
	 * <p>
	 * 可以通过MyDateTime.now().get(MyDateTime.MINUTE_FIELD)来获取当前时间的分钟
	 * </p>
	 */
	public static final int MINUTE_FIELD = java.util.Calendar.MINUTE;
	/**
	 * 秒
	 * <p>
	 * 可以通过MyDateTime.now().get(MyDateTime.SECOND_FIELD)来获取当前时间的秒
	 * </p>
	 */
	public static final int SECOND_FIELD = java.util.Calendar.SECOND;
	/**
	 * 毫秒
	 * <p>
	 * 可以通过MyDateTime.now().get(MyDateTime.MILLISECOND_FIELD)来获取当前时间的毫秒
	 * </p>
	 */
	public static final int MILLISECOND_FIELD = java.util.Calendar.MILLISECOND;
	private java.util.Calendar c; // 日历类

	/**
	 * 获取一个MyDateTime,此MyDateTime尚未初始化,表示的时间是1970-1-1 00:00:00.000
	 * <p>
	 * 要获取当前系统时间,请用MyDateTime.now();
	 * </p>
	 */
	public DateTimeUtil() {
		c = Calendar.getInstance();
		c.clear();
	}

	/**
	 * 设置时间
	 * <p>
	 * 可以传入一个时间对象，将会被转换为MyDateTime类型
	 * </p>
	 * 
	 * @param date
	 *            时间对象
	 */
	public DateTimeUtil(java.util.Date date) {
		c = Calendar.getInstance();
		c.setTime(date);
	}

	/**
	 * 设置时间
	 * <p>
	 * 可以传入一个日历对象，将会被转换为MyDateTime类型
	 * </p>
	 * 
	 * @param calendar
	 *            日历对象
	 */
	public DateTimeUtil(java.util.Calendar calendar) {
		this.c = calendar;
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @return MyDateTime 当前系统时间
	 */
	public static DateTimeUtil now() {
		Calendar calendar = Calendar.getInstance();
		return new DateTimeUtil(calendar);
	}

	/**
	 * 用毫秒来设置时间, 时间的基数是1970-1-1 00:00:00.000;
	 * <p>
	 * 比如,new MyDateTime(1000) 则表示1970-1-1 00:00:01.000;<br>
	 * 用负数表示基础时间以前的时间
	 * </p>
	 * 
	 * @param milliseconds
	 *            毫秒
	 */
	public DateTimeUtil(long milliseconds) {
		c = Calendar.getInstance();
		c.setTimeInMillis(milliseconds);
	}

	/**
	 * 转换为Date类型
	 * 
	 * @return Date时间
	 */
	public Date toDate() {
		return c.getTime();
	}

	/**
	 * 转换成 日历对象
	 * 
	 * @return Calendar对象
	 */
	public java.util.Calendar toCalendar() {
		return c;
	}

	/**
	 * 转换成java.sql.Date(yyyy-MM-dd)日期
	 * 
	 * @return java.sql.Date日期
	 */
	public java.sql.Date toSqlDate() {
		return new java.sql.Date(c.getTimeInMillis());
	}

	/**
	 * 转换为java.sql.Time(hh:mm:ss)时间
	 * 
	 * @return java.sql.Time时间
	 */
	public java.sql.Time toSqlTime() {
		return new java.sql.Time(c.getTimeInMillis());
	}

	/**
	 * 转换为java.sql.Timestamp(时间戳)
	 * 
	 * @return java.sql.Timestamp时间戳
	 */
	public java.sql.Timestamp toSqlTimestamp() {
		return new java.sql.Timestamp(c.getTimeInMillis());
	}

	/**
	 * 解析时间
	 * <p>
	 * 根据MyDateTime中的DEFAULT_TIME_FORMAT_PATTERN规则转换为hh:mm:ss或hh:mm格式
	 * </p>
	 * 
	 * @param time
	 *            字符串格式时间
	 * @return MyDateTime 日期时间对象
	 */
	public static DateTimeUtil parseTime(String time) throws java.text.ParseException {
		try {
			return DateTimeUtil.parseDateTime(time, DateTimeUtil.DEFAULT_TIME_FORMAT_PATTERN);
		} catch (ParseException e) {
			return DateTimeUtil.parseDateTime(time, DateTimeUtil.DEFAULT_TIME_HHmm_FORMAT_PATTERN);
		}
	}

	/**
	 * 解析日期
	 * <p>
	 * 根据MyDateTime中的DEFAULT_DATE_FORMAT_PATTERN规则转换为yyyy-MM-dd格式
	 * </p>
	 * 
	 * @param date
	 *            字符串格式日期
	 * @return MyDateTime 日期时间类
	 */
	public static DateTimeUtil parseDate(String date) throws java.text.ParseException {
		return DateTimeUtil.parseDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT_PATTERN);
	}

	/**
	 * 解析日期时间
	 * <p>
	 * 根据MyDateTime中的DEFAULT_DATE_TIME_FORMAT_PATTERN规则转换为yyyy-MM-dd HH:mm:ss格式
	 * </p>
	 * 
	 * @param datetime
	 *            字符串格式日期时间
	 * @return MyDateTime 日期时间对象
	 */
	public static DateTimeUtil parseDateTime(String datetime) throws java.text.ParseException {
		DateTimeUtil result = null;
		// 尝试按yyyy-MM-dd HH:mm:ss分析
		try {
			result = DateTimeUtil.parseDateTime(datetime, DateTimeUtil.DEFAULT_DATE_TIME_FORMAT_PATTERN);
		} catch (ParseException e) {
			// 解析错误
			result = null;
		}

		// 尝试按yyyy-MM-dd HH:mm分析
		if (null == result) {
			try {
				result = DateTimeUtil.parseDateTime(datetime, DateTimeUtil.DEFAULT_DATE_TIME_HHmm_FORMAT_PATTERN);
			} catch (ParseException e) {
				// 解析错误
				result = null;
			}
		}

		// 尝试按yyyy-MM-dd HH分析
		if (null == result) {
			try {
				result = DateTimeUtil.parseDateTime(datetime, DateTimeUtil.DEFAULT_DATE_TIME_HH_FORMAT_PATTERN);
			} catch (ParseException e) {
				// 解析错误
				result = null;
			}
		}

		// 尝试按yyyy-MM-dd分析
		if (null == result) {
			try {
				result = DateTimeUtil.parseDate(datetime);
			} catch (ParseException e) {
				// 解析错误
				result = null;
			}
		}

		// 尝试按时间分析
		if (null == result) {
			result = DateTimeUtil.parseTime(datetime);
		}
		return result;
	}

	/**
	 * 用指定的pattern分析字符串
	 * <p>
	 * pattern的用法参见java.text.SimpleDateFormat
	 * </p>
	 * 
	 * @param datetime
	 *            字符串格式日期时间
	 * @param pattern
	 *            日期解析规则
	 * @return MyDateTime 日期时间对象
	 * @see java.text.SimpleDateFormat
	 */
	public static DateTimeUtil parseDateTime(String datetime, String pattern) throws java.text.ParseException {
		SimpleDateFormat fmt = (SimpleDateFormat) DateFormat.getDateInstance();
		fmt.applyPattern(pattern);
		return new DateTimeUtil(fmt.parse(datetime));
	}

	/**
	 * 转换为 DEFAULT_DATE_FORMAT_PATTERN (yyyy-MM-dd) 格式字符串
	 * 
	 * @return yyyy-MM-dd格式字符串
	 */
	public String toDateString() {
		return toDateTimeString(DateTimeUtil.DEFAULT_DATE_FORMAT_PATTERN);
	}

	/**
	 * 转换为 DEFAULT_TIME_FORMAT_PATTERN (HH:mm:ss) 格式字符串
	 * 
	 * @return HH:mm:ss 格式字符串
	 */
	public String toTimeString() {
		return toDateTimeString(DateTimeUtil.DEFAULT_TIME_FORMAT_PATTERN);
	}

	/**
	 * 转换为 DEFAULT_DATE_TIME_FORMAT_PATTERN (yyyy-MM-dd HH:mm:ss) 格式字符串
	 * 
	 * @return yyyy-MM-dd HH:mm:ss 格式字符串
	 */
	public String toDateTimeString() {
		return toDateTimeString(DateTimeUtil.DEFAULT_DATE_TIME_FORMAT_PATTERN);
	}

	/**
	 * 使用日期转换pattern
	 * <p>
	 * pattern的用法参见java.text.SimpleDateFormat
	 * </p>
	 * 
	 * @param pattern
	 *            日期解析规则
	 * @return 按规则转换后的日期时间字符串
	 */
	public String toDateTimeString(String pattern) {
		SimpleDateFormat fmt = (SimpleDateFormat) DateFormat.getDateInstance();
		fmt.applyPattern(pattern);
		return fmt.format(c.getTime());
	}

	/**
	 * 获取MyDateTime所表示时间的某个度量的值
	 * 
	 * @param field
	 *            int 取值为:<br>
	 *            MyDateTime.YEAR_FIELD -- 返回年份<br>
	 *            MyDateTime.MONTH_FIELD -- 返回月份,一月份返回1,二月份返回2,依次类推<br>
	 *            MyDateTime.DAY_FIELD -- 返回当前的天(本月中的)<br>
	 *            MyDateTime.HOUR_FIELD -- 返回小时数(本天中的),24小时制<br>
	 *            MyDateTime.MINUTE_FIELD -- 返回分钟数(本小时中的)<br>
	 *            MyDateTime.SECOND_FIELD -- 返回秒数(本分钟中的)<br>
	 *            MyDateTime.MILLISECOND_FIELD -- 返回毫秒数(本秒中的)
	 * @return int field对应的值
	 */
	public int get(int field) {
		// 月份需要+1(月份从0开始)
		if (DateTimeUtil.MONTH_FIELD == field) {
			return c.get(field) + 1;
		} else {
			return c.get(field);
		}
	}

	/**
	 * 返回自 1970-1-1 0:0:0 至此时间的毫秒数
	 * 
	 * @return long 毫秒数
	 */
	public long getTimeInMillis() {
		return c.getTimeInMillis();
	}

	/**
	 * 设置field字段的值
	 * 
	 * @param field
	 *            int 取值为:<br>
	 *            MyDateTime.YEAR_FIELD -- 年份<br>
	 *            MyDateTime.MONTH_FIELD -- 月份,一月份从1开始<br>
	 *            MyDateTime.DAY_FIELD -- 当前的天(本月中的)<br>
	 *            MyDateTime.HOUR_FIELD -- 小时数(本天中的),24小时制<br>
	 *            MyDateTime.MINUTE_FIELD -- 分钟数(本小时中的)<br>
	 *            MyDateTime.SECOND_FIELD -- 秒数(本分钟中的)<br>
	 *            MyDateTime.MILLISECOND_FIELD -- 毫秒数(本秒中的)
	 * @param value
	 */
	public void set(int field, int value) {
		// 月份需要-1(月份从0开始)
		if (DateTimeUtil.MONTH_FIELD == field) {
			c.set(field, value - 1);
		} else {
			c.set(field, value);
		}
	}

	/**
	 * 设置MyDateTime日期的年/月/日
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 */
	public void set(int year, int month, int day) {
		set(DateTimeUtil.YEAR_FIELD, year);
		set(DateTimeUtil.MONTH_FIELD, month);
		set(DateTimeUtil.DAY_FIELD, day);
	}

	/**
	 * 设置MyDateTime日期的年/月/日/小时
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            小时
	 */
	public void set(int year, int month, int day, int hour) {
		set(year, month, day);
		set(DateTimeUtil.HOUR_FIELD, hour);
	}

	/**
	 * 设置MyDateTime日期的年/月/日/小时/分钟
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 */
	public void set(int year, int month, int day, int hour, int minute) {
		set(year, month, day, hour);
		set(DateTimeUtil.MINUTE_FIELD, minute);
	}

	/**
	 * 设置MyDateTime日期的年/月/日/小时/分钟/秒
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 * @param second
	 *            秒
	 */
	public void set(int year, int month, int day, int hour, int minute, int second) {
		set(year, month, day, hour, minute);
		set(DateTimeUtil.SECOND_FIELD, second);
	}

	/**
	 * 设置MyDateTime日期的年/月/日/小时/分钟/秒/毫秒
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 * @param second
	 *            秒
	 * @param milliSecond
	 *            毫秒
	 */
	public void set(int year, int month, int day, int hour, int minute, int second, int milliSecond) {
		set(year, month, day, hour, minute, second);
		set(DateTimeUtil.MILLISECOND_FIELD, milliSecond);
	}

	/**
	 * 对field值进行相加
	 * <p>
	 * add() 的功能非常强大，add 可以对 MyDateTime 的字段进行计算。<br>
	 * 如果需要减去值，那么使用负数值就可以了，如 add(field, -value)。<br>
	 * 或者调用MyDateTime.reduce(int,int)进行日期相减
	 * </p>
	 * 
	 * @param field
	 *            int 取值为:<br>
	 *            MyDateTime.YEAR_FIELD -- 年份<br>
	 *            MyDateTime.MONTH_FIELD -- 月份,一月份从1开始<br>
	 *            MyDateTime.DAY_FIELD -- 当前的天(本月中的)<br>
	 *            MyDateTime.HOUR_FIELD -- 小时数(本天中的),24小时制<br>
	 *            MyDateTime.MINUTE_FIELD -- 分钟数(本小时中的)<br>
	 *            MyDateTime.SECOND_FIELD -- 秒数(本分钟中的)<br>
	 *            MyDateTime.MILLISECOND_FIELD -- 毫秒数(本秒中的)
	 * @param amount
	 *            数量(如果数量小于0则为相减)
	 */
	public void add(int field, int amount) {
		c.add(field, amount);
	}

	/**
	 * 对field值进行相减
	 * <p>
	 * 对add() 的功能进行封装，add 可以对 Calendar 的字段进行计算。<br>
	 * 如果需要减去值，那么使用负数值就可以了，如 add(field, -value)。<br>
	 * 详细用法参见Calendar.add(int,int)
	 * </p>
	 * 
	 * @param field
	 *            int 取值为:<br>
	 *            MyDateTime.YEAR_FIELD -- 年份<br>
	 *            MyDateTime.MONTH_FIELD -- 月份,一月份从1开始<br>
	 *            MyDateTime.DAY_FIELD -- 当前的天(本月中的)<br>
	 *            MyDateTime.HOUR_FIELD -- 小时数(本天中的),24小时制<br>
	 *            MyDateTime.MINUTE_FIELD -- 分钟数(本小时中的)<br>
	 *            MyDateTime.SECOND_FIELD -- 秒数(本分钟中的)<br>
	 *            MyDateTime.MILLISECOND_FIELD -- 毫秒数(本秒中的)
	 * @param amount
	 *            数量(如果数量小于0则为相加)
	 */
	public void reduce(int field, int amount) {
		c.add(field, -amount);
	}

	/**
	 * 判断此 MyDateTime 表示的时间是否在指定 Object 表示的时间之后，返回判断结果。
	 * <p>
	 * 此方法等效于：compareTo(when) > 0<br>
	 * 当且仅当 when 是一个 MyDateTime 实例时才返回 true。否则该方法返回 false。
	 * 
	 * @param when
	 *            要比较的 Object
	 * @return 如果此 MyDateTime 的时间在 when 表示的时间之后，则返回 true；否则返回 false。
	 */
	public boolean after(Object when) {
		if (when instanceof DateTimeUtil) {
			return c.after(((DateTimeUtil) when).c);
		}
		return c.after(when);
	}

	/**
	 * 判断此 MyDateTime 表示的时间是否在指定 Object 表示的时间之前，返回判断结果。
	 * <p>
	 * 此方法等效于：compareTo(when) < 0<br>
	 * 当且仅当 when 是一个 MyDateTime 实例时才返回 true。否则该方法返回 false。
	 * </p>
	 * 
	 * @param when
	 *            要比较的 Object
	 * @return 如果此 Calendar 的时间在 when 表示的时间之前，则返回 true；否则返回 false。
	 */
	public boolean before(Object when) {
		if (when instanceof DateTimeUtil) {
			return c.before(((DateTimeUtil) when).c);
		}
		return c.before(when);
	}

	/**
	 * 创建并返回此对象的一个副本
	 * 
	 * @return 日期时间对象
	 */
	@Override
	public Object clone() {
		return new DateTimeUtil((Calendar) c.clone());
	}

	/**
	 * 返回该此日历的哈希码
	 * 
	 * @return 此对象的哈希码值。
	 * @see Object
	 */
	@Override
	public int hashCode() {
		return c.hashCode();
	}

	/**
	 * 将此 MyDateTime 与指定 Object 比较。
	 * 
	 * @param obj
	 *            - 要与之比较的对象。
	 * @return 如果此对象等于 obj，则返回 true；否则返回 false。
	 * @see Object
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DateTimeUtil) {
			return c.equals(((DateTimeUtil) obj).toCalendar());
		}
		if (obj instanceof Calendar) {
			return c.equals(obj);
		}
		if (obj instanceof java.util.Date) {
			return c.getTime().equals(obj);
		}
		return false;
	}
	
	
	/**
	 * 获取当前UTC时间字符串形式
	 * @return  UTC时间
	 */
    public static String getNowUTCTimeStr() {
        // 1、取得本地时间：  
        Calendar cal = Calendar.getInstance() ;  
        // 2、取得时间偏移量：  
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);  
        // 3、取得夏令时差：  
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);  
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：  
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));  
        return new DateTimeUtil(cal.getTime()).toDateTimeString();
    }  
    
    /**
     * 获取当前UTC时间Date数据类型
     * @return UTC时间
     */
    public static Date getNowUtcDate(){
    	 // 1、取得本地时间：  
        Calendar cal = Calendar.getInstance() ;  
        // 2、取得时间偏移量：  
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);  
        // 3、取得夏令时差：  
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);  
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：  
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset)); 
        return cal.getTime();
    }
    
    /**
     * 根据配置类型获取当前时间
     * @param dateType 
     * @return
     */
	public static Date getNowTime(Integer dateType) {
		if (DateTypeEnumUtil.UTC.getValue() == dateType) {
			return getNowUtcDate();
		} else {
			return new Date();
		}
	}
	
	/**
	 * 从UTC时间转换为当地时间
	 * @param UTCDate UTC时间
	 * @return 从UTC时间传唤为当地时间的字符串形式
	 */
	public static String getLocalTimeStrFromUTC(Date UTCDate){  
		/*
		 * 将UTC Date转换为本地 Date
		 * 将date转换为字符串形式
		 */
		Date localTime = getLocalTimeFromUTC(UTCDate);
		return new DateTimeUtil(localTime).toDateTimeString();
    }
	
	/**
	 * 
	 * @param UTCDate
	 * @return
	 */
	public static Date getLocalTimeFromUTC(Date UTCDate){
        Calendar cal = Calendar.getInstance() ;
        cal.setTime(UTCDate);
        
        // 取得时间偏移量：  
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);  
        // 取得夏令时差：  
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);  
        cal.add(Calendar.MILLISECOND, +(zoneOffset + dstOffset)); 
        return cal.getTime();
	}
	
	/**
	 * 获取默认时间
	 * @return 时间
	 * @throws ParseException 转换异常
	 */
	public static Date getDefaultDate(){
		String date = "1970-01-01";
		DateTimeUtil dateUtil = new DateTimeUtil();
		try {
			dateUtil = parseDate(date);
		} catch (ParseException e) {
			return dateUtil.toDate();
		}
		return dateUtil.toDate();
	}
	
    
}