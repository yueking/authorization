package com.yueking.security.core.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@Data
@Entity
public class Role extends Base implements GrantedAuthority, Serializable {
    private String roleName;
    private String roleTag;
    private String roleDesc;

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public void removePermission(Permission permission) {
        this.permissions.remove(permission);
    }

    @ManyToMany
    @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "perm_id")})
    public List<Permission> permissions = new LinkedList<>();

    @Override
    public String getAuthority() {
        return this.roleTag;
    }
}
