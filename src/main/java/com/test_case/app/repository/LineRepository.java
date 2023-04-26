package com.test_case.app.repository;

import com.test_case.app.model.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LineRepository extends JpaRepository<Line, Long> {

    List<Line> findByName(String name);
}
