package com.test_case.app.repository;

import com.test_case.app.model.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineRepository extends JpaRepository<Line, Long>, JpaSpecificationExecutor {

    List<Line> findByName(String name);
}
