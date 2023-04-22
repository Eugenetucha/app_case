package com.test_case.app.repository;

import com.test_case.app.model.entity.TV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TVRepository extends JpaRepository<TV, Long> {
    @Query(
            value = "SELECT u FROM TV u WHERE u.name like :name")
    List<TV> findByName(@Param("name")String name);
}
