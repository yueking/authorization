package com.yueking.security.core.repository;

import com.yueking.security.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Modifying
    @Query("update User t set t.del = 1 where t.username = ?1")
    void deleteById(String id);
}
