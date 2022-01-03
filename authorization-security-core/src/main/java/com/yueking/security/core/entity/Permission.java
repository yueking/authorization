package com.yueking.security.core.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import java.io.Serializable;


@Data
@Entity
public class Permission extends Base implements GrantedAuthority, Serializable {
    private String permName;
    private String permTag;
    private String permDesc;

    @Override
    public String getAuthority() {
        return this.permTag;
    }
}
