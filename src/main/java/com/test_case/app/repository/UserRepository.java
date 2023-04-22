package com.test_case.app.repository;

import com.test_case.app.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
        User findByUsername(String username);
}
