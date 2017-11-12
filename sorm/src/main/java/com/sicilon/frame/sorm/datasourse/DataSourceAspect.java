package com.sicilon.frame.sorm.datasourse;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sicilon.frame.sorm.utils.StringUtil;


/**
 * 切换数据源Advice
 */
//@Aspect
//@Order(-1)// 保证该AOP在@Transactional之前执行
//@Component
public class DataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
    @Pointcut("execution(* com.sicilon.ycsys.usercenter.dao.interfaces.*.*(..))")
	public void interceptor() {
	}

    //@Before("@annotation(ds)"), TargetDataSource ds
    @Before("interceptor()")
    public void changeDataSource(JoinPoint point){
    	System.out.println("!!!!!!");
		try {
			Class[] parameterTypes = new Class[point.getArgs().length]; 
			Object[] args = point.getArgs();    
			for(int i=0; i<args.length; i++) {    
			    if(args[i] != null) {    
			        parameterTypes[i] = args[i].getClass();    
			    }else {    
			        parameterTypes[i] = null;    
			    }    
			}    
			Signature signature = point.getSignature();    
			MethodSignature methodSignature = (MethodSignature)signature;    
			Method targetMethod = methodSignature.getMethod(); 
			
			//获取类头上的注解
			TargetDataSource annotationClass = targetMethod.getDeclaringClass().getAnnotation(TargetDataSource.class);
			Method declaredMethod2 = targetMethod.getDeclaringClass().getDeclaredMethod(point.getSignature().getName(), parameterTypes);
			//获取方法的注解
			TargetDataSource annotationMthod = declaredMethod2.getAnnotation(TargetDataSource.class);
			String methodDataSource = null;String classDataSource = null;
			try {
				methodDataSource = annotationMthod.name();//方法
			} catch (Exception e) {
			}
			try {
				classDataSource = annotationClass.name();//类
			} catch (Exception e) {
			}
			/*
			 * 获取数据源  采取就近原则
			 * 方法上有指定数据源，就使用方法上指定的
			 * 方法没有使用类头指定数据源
			 * 类头没有就使用默认数据源
			 */
			if(StringUtil.isNotBlank(methodDataSource)){
				DataSourceHolder.putDataSource(methodDataSource);
			}else{
				if(StringUtil.isNotBlank(classDataSource)){
					DataSourceHolder.putDataSource(classDataSource);
				}
			}
		} catch (Exception e) {
			throw new  RuntimeException(e.getMessage());
		}
    }
    
    
    @After("interceptor()")//, TargetDataSource ds  && @annotation(ds)
    public void restoreDataSource(JoinPoint point) {
        //logger.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        DataSourceHolder.clearDataSource();
    }

}

