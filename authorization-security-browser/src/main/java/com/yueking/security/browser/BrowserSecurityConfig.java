package com.yueking.security.browser;

import com.yueking.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }


}
