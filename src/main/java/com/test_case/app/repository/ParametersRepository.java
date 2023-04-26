package com.test_case.app.repository;

import com.test_case.app.model.entity.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ParametersRepository extends JpaRepository<Parameters, Long> {

    List<Parameters> findByName(String name);
}
