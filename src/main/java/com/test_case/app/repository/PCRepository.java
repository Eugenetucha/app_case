package com.test_case.app.repository;

import com.test_case.app.model.entity.PC;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PCRepository extends CrudRepository<PC, Long> {
    @Query(
            value = "SELECT u FROM PC u WHERE u.name like :name")
    public List<PC> findByName(@Param("name")String name);
}
