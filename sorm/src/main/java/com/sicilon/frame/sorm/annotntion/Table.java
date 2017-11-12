package com.sicilon.frame.sorm.annotntion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表名注解 作用于类头
 * @author SmallFour
 * @date 创建时间：2017年5月5日 下午3:46:55
 * @version 1.0 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
    public String value();
}
