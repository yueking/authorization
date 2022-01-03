package com.yueking.security.core.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name="sys_permission")
public class Permission extends Base implements GrantedAuthority, Serializable {
    private String permName;
    private String permTag;
    private String permDesc;

    @Override
    public String getAuthority() {
        return this.permTag;
    }
}
