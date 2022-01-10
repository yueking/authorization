package com.yueking.security.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Base implements Serializable {
    public interface insert{};
    public interface update{};
    public interface SimpleView{}
    public interface DetailView extends SimpleView{}

    @JsonView(SimpleView.class)
    @Column(name = "del")
    private boolean del;

    @JsonView(DetailView.class)
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @JsonIgnore
    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 64)
    private String createdBy;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "updated_date")
    private Date updatedDate;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "updated_by", length = 64)
    private String updatedBy;

    @JsonIgnore
    @Transient
    private Date startDate;

    @JsonIgnore
    @Transient
    private Date endDate;
}
