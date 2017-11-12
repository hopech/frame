package com.sicilon.frame.sorm.annotntion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表字段主键 作用于 java字段
 * @author SmallFour
 * @date 创建时间：2017年5月5日 下午3:45:20
 * @version 1.0 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
	public String value() default "";
}
