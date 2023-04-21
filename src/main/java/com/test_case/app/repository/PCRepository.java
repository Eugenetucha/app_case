package com.test_case.app.repository;

import com.test_case.app.model.entity.PC;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PCRepository extends CrudRepository<PC, Long> {
    @Query(
            value = "SELECT * FROM pc u WHERE u.name ilike ?1",
            nativeQuery = true)
    public PC findByName(String name);
}
