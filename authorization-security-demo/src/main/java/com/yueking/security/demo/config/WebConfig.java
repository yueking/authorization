package com.yueking.security.demo.config;

import com.yueking.security.demo.filter.TimeFilter;
import com.yueking.security.demo.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        // 1.注册过滤器
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        // 2.匹配过滤器
        List<String> urlPatterns = new LinkedList<>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

    //todo 异步请求处理支持

    // @Override
    // public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    //     configurer.registerDeferredResultInterceptors(timeInterceptorAsync);
    // }
}
