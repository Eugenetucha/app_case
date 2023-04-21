package com.test_case.app.repository;

import com.test_case.app.model.entity.TV;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TVRepository extends CrudRepository<TV, Long> {
    @Query(
            value = "SELECT u FROM TV u WHERE u.name like :name")
    public List<TV> findByName(@Param("name")String name);
}
