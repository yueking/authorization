package com.yueking.security.core.base;

import lombok.Data;

@Data
public class ResultResponse {
    private Object data;

    public ResultResponse(Object data) {
        this.data = data;
    }
}
