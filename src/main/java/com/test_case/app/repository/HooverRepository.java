package com.test_case.app.repository;

import com.test_case.app.model.entity.Hoover;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HooverRepository extends CrudRepository<Hoover, Long> {
    @Query(
            value = "SELECT u FROM Hoover u WHERE u.name like :name")
    public List<Hoover> findByName(@Param("name")String name);
}
