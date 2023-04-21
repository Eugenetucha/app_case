package com.test_case.app.repository;

import com.test_case.app.model.entity.TV;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TVRepository extends CrudRepository<TV, Long> {
    @Query(
            value = "SELECT * FROM tv u WHERE u.name ilike ?1",
            nativeQuery = true)
    public TV findByName(String name);
}
