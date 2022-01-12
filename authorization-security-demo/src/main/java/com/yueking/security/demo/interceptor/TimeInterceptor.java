package com.yueking.security.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TimeInterceptor implements HandlerInterceptor {
    /**
     * 方法执行前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime",System.currentTimeMillis());

        if(handler instanceof  HandlerMethod){
            String className = ((HandlerMethod) handler).getBean().getClass().getName();
            String methodName = ((HandlerMethod) handler).getMethod().getName();
            System.out.println("--preHandle\t\t\t time interceptor 执行:"+ className +"."+ methodName);
        }else if(handler instanceof ResourceHttpRequestHandler){
            System.out.println("--preHandle\t\t\t time interceptor 执行:");
        }
        return true;
    }

    /**
     * 方法正常执行后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("--postHandle\t\t time interceptor 耗时:"+(System.currentTimeMillis() - startTime));
    }

    /**
     * 方法执行后 无论是否有异常 最后都会执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("--afterCompletion\t time interceptor 耗时:"+(System.currentTimeMillis() - startTime)+"\texception: "+ex);
    }
}
