package com.cc.frame.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 标明字段为日期类型字段
 * @author CHENWEIJIA
 * @version V1.0, 2017年11月19日
 * @see
 * @since V1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateFormat {
}
