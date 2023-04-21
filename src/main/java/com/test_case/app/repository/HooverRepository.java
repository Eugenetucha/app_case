package com.test_case.app.repository;

import com.test_case.app.model.entity.Hoover;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HooverRepository extends CrudRepository<Hoover, Long> {
    @Query(
            value = "SELECT * FROM hoover u WHERE u.name ilike ?1",
            nativeQuery = true)
    public List<Hoover> findByName(String name);
}
