package com.yueking.security.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.yueking.security.core.base.valid.MyConstraint;
import com.yueking.security.core.base.valid.UserStatus;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name="sys_user")
public class User extends Base implements UserDetails, Serializable {
    @Id
    @JsonView(SimpleView.class)
    @NotBlank
    // @MyConstraint(message = "用户已存在")
    private String username;

    @JsonView(DetailView.class)
    @NotBlank(message = "密码不能为空")
    @Length(min = 3, max = 20, message = "密码长度在[3,20]之间")
    private String password;

    @JsonView(DetailView.class)
    private boolean accountNonExpired = true;
    @JsonView(DetailView.class)
    private boolean accountNonLocked = true;
    @JsonView(DetailView.class)
    private boolean credentialsNonExpired = true;
    @JsonView(DetailView.class)
    private boolean enabled = true;

    @JsonView(DetailView.class)
    @UserStatus
    private Integer status;

    public User(){}
    public User(String username){
        this.username = username;
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public List<Role> roles = new LinkedList<>();

    @JsonView(DetailView.class)
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