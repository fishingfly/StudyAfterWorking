package com.zy.test.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.zy.test.annotation.ArchivesLog;

/**
 * web 异常切面
 * @author user
 *
 */
@Aspect
public class WebExceptionAspect {
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void webPointcut() {}
    
    @AfterThrowing(pointcut = "webPointcut()", throwing = "e")
    public void handleThrowing(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        //开始打log
        System.out.println("异常:" + e.getMessage());
        System.out.println("异常所在类：" + className);
        System.out.println("异常所在方法：" + methodName);
        System.out.println("异常中的参数：");
        System.out.println(methodName);
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i].toString());
        }
    }
    

    @Before("execution(* com.zy.test.controller.*.*(..))")
    public void beforeProcess(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        //在项目中最好记录当前操作的时间和用户
        System.out.println("操作所在类：" + className);
        System.out.println("操作所在方法：" + methodName);
        System.out.println("操作中的参数：");
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i].toString());
        }
    }
    
    @AfterReturning(value = "execution(* com.zy.test.controller.*.*(..)))", returning = "returnVal")
    public void returnProcess(JoinPoint joinPoint, Object returnVal) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class targetClass = null;
        String operationName = "";
        try {
            targetClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs != null && clazzs.length == args.length&&
                        method.getAnnotation(ArchivesLog.class)!=null) {
                    operationName = method.getAnnotation(ArchivesLog.class).operationName();
                    break;
                }
            }
        }
        System.out.println("操作名称：" + operationName);
        System.out.println("方法正常返回的值：" + returnVal);
    }
    
}