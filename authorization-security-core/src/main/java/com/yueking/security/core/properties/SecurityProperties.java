package com.yueking.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "yueking.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
}
