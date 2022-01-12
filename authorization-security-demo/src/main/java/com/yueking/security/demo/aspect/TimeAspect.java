package com.yueking.security.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.yueking.security.demo.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint point) throws Throwable {
        // System.out.println("time aspect 开始");
        long start = System.currentTimeMillis();
        // 获取方法名称等数据
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        System.out.print("--"+currentMethod.getName()+"(");
        for (int i = 0; i < point.getArgs().length; i++) {
            if (i == 0) {
                System.out.print(point.getArgs()[i]);
            }else {
                System.out.print(",\t"+point.getArgs()[i]);
            }
        }
        System.out.print(") ");

        Object proceed = point.proceed();
        System.out.println("\ttime aspect 结束\t耗时:"+(System.currentTimeMillis() - start));
        return proceed;
    }
}
