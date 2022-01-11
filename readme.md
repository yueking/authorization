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

