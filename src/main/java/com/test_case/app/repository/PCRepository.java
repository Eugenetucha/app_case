package com.test_case.app.repository;

import com.test_case.app.model.entity.PC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PCRepository extends JpaRepository<PC, Long> {
    List<PC> findByName(String name);
}
