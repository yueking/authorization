##### 自定义异常处理
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
##### 自定义Filter
###### 1. 方式一编写

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



###### 2. 方式一引用

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
###### 3. Filter总结

```bash
1.方式一 优点:简单			缺点:全局匹配所有url过滤
2.方式二 优点:可以引用第三方filter、集中配置并可控制具体的url进行过滤
3.Filter可以过滤所有的请求，但是无法感知具体controller及方法的信息
```
##### 自定义拦截器

