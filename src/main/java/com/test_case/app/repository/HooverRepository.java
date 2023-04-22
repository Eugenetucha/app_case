package com.test_case.app.repository;

import com.test_case.app.model.entity.Hoover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HooverRepository extends JpaRepository<Hoover, Long> {
    List<Hoover> findByName(String name);
}
