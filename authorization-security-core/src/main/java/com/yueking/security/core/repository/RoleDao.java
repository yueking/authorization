package com.yueking.security.core.repository;

import com.yueking.security.core.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {
    Role getRoleByRoleName(String name);
}
