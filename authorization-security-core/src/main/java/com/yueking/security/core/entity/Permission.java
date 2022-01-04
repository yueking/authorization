package com.yueking.security.core.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name="sys_permission")
public class Permission extends Base implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonView(Base.SimpleView.class)
    private String id;
    @JsonView(Base.SimpleView.class)
    private String permName;
    @JsonView(Base.DetailView.class)
    private String permTag;
    @JsonView(Base.SimpleView.class)
    private String permDesc;

    @Override
    public String getAuthority() {
        return this.permTag;
    }
}
