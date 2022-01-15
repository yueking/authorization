package com.yueking.security.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {
    private String loginPage="/login.html";
    private int rememberMeSeconds = 3600;
}
