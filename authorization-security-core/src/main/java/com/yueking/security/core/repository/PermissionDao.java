package com.yueking.security.core.repository;

import com.yueking.security.core.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends JpaRepository<Permission, String> {
    Permission getSysPermissionByPermName(String permissionName);
}
