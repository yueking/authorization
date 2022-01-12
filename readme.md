#### 自定义异常处理
###### html

1. src/main/java/resources/resources/error/
2. 放入已状态码命名的html: 404.html 500.html

###### json
1. 创建 @ControllerAdvice exceptionHandle 类
2. 配置类 处理的异常
2. 注意ExceptionHandlerAdvice类所在的包必须在项目启动类之下的包
2. 返回统一的自定义ResponseEntity

```java
@ControllerAdvice
public class ExceptionHandlerAdvice {
  	@ResponseBody
    @ExceptionHandler(UserNotExistException.class)
		@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleEx(UserNotExistException e) {
        Map<String,Object> result = new HashMap<>();
        result.put("username", e.getUsername());
        result.put("message", e.getMessage());
        return result;
    }
}
```
#### RESTful API 的拦截

##### 自定义过滤器Filter

###### 方式一编写

```java
@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter is init");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter starting...");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        long time = System.currentTimeMillis() - start;
        System.out.println("time filter 耗时:"+time);
        System.out.println("time filter finish");
    }
    @Override
    public void destroy() {
        System.out.println("time filter is destroy");
    }
}
```



######  方式一引用

```java
@Configuration
public class WebConfig {
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
}
```
###### Filter总结

```bash
1.方式一 优点:简单			缺点:全局匹配所有url过滤
2.方式二 优点:可以引用第三方filter、集中配置并可控制具体的url进行过滤
3.Filter可以过滤所有的请求，但是无法感知具体controller及方法的信息
```
##### 自定义拦截器Interceptor
###### 编写

```java
@Component
public class TimeInterceptor implements HandlerInterceptor {
    /**
     * 方法执行前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime",System.currentTimeMillis());
        System.out.println("--preHandle\t\t\t time interceptor 执行:"+((HandlerMethod)handler).getBean().getClass().getName()+"."+((HandlerMethod)handler).getMethod().getName());
        return true;
    }

    /**
     * 方法正常执行后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("--postHandle\t\t time interceptor 耗时:"+(System.currentTimeMillis() - startTime));
    }

    /**
     * 方法执行后 无论是否有异常 最后都会执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("--afterCompletion\t time interceptor 耗时:"+(System.currentTimeMillis() - startTime)+"\texception: "+ex);
    }
}
```
###### 配置

```java

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}

```

###### 总结

```java
Interceptor 需要配置
Interceptor 比Filter能细致由于其是SpringMvc的组件其能感知具体Controller及方法
```
##### 自定义切片Aspect

###### 切入点与方法

	- @Before
	- @After
	- @AfterThrowing
	- @AfterReturning
	- @Around

```java
@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.yueking.security.demo.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint point) throws Throwable {
        System.out.println("time aspect 开始");
        long start = System.currentTimeMillis();
        Object proceed = point.proceed();
        System.out.println("time aspect 结束\t耗时:"+(System.currentTimeMillis() - start));
        return proceed;
    }
}

```

拦截器总结

```
Filter			:能获取原始 httpRequest httpResponse
Interceptor	:能获取原始 httpRequest httpResponse class methodName
Aspect			:参数的值与方法名 但是获取不到原始 request response
```

