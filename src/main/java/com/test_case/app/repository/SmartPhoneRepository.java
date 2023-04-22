package com.test_case.app.repository;

import com.test_case.app.model.entity.SmartPhone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmartPhoneRepository extends JpaRepository<SmartPhone, Long> {
    List<SmartPhone> findByName(String name);
}
