package com.zy.test.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented
public @interface ArchivesLog {
    /**
     * 操作类型
     * @return
     */
    public String operationType() default "";
    
    /**
     * 操作名称
     * @return
     */
    public String operationName() default "";

}
