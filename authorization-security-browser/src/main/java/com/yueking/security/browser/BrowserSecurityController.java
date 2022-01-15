package com.yueking.security.browser;

import com.yueking.security.core.base.ResultResponse;
import com.yueking.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    final SecurityProperties securityProperties;

    public BrowserSecurityController(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * 需要身份验证时，跳转到这里进行下一处理
     * 1.html   请求进行跳转登录
     * 2.非html 请求返回 json 并告知其需要身份验证的消息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResultResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求:" + targetUrl);
            //todo 判断是浏览器还是restful请求 这个判断方式需要改进，读取请求头中的信息进行判断
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                // html 请求跳转
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            } else {

            }
        }
        return new ResultResponse("请进行身份认证");
    }
}
