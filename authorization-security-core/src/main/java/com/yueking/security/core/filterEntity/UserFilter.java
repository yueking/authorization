package com.yueking.security.core.filterEntity;

import com.yueking.security.core.entity.User;
import lombok.Data;

@Data
public class UserFilter extends User {
    public String id;
}
