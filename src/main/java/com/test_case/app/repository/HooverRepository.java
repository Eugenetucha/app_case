package com.test_case.app.repository;

import com.test_case.app.model.entity.Hoover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HooverRepository extends JpaRepository<Hoover, Long> {
    public List<Hoover> findByName(String name);
}
