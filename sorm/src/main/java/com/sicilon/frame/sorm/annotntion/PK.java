package com.sicilon.frame.sorm.annotntion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 主键注解 作用于 java字段
 * @author SmallFour
 * @date 创建时间：2017年5月5日 下午3:46:42
 * @version 1.0 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PK {
	public String value() default "";
}