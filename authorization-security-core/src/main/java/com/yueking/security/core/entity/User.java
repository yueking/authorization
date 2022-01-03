package com.yueking.security.core.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name="sys_user")
public class User extends Base implements UserDetails, Serializable {
    private String username;
    private String password;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public User(){}
    public User(String username){
        this.username = username;
    }

    @ManyToMany
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public List<Role> roles = new LinkedList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List authorities = new LinkedList();
        for (Role role : roles) {
            authorities.add(role);
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                authorities.add(permission);
            }
        }
        return authorities;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }
}