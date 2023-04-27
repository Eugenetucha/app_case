package com.test_case.app.repository;

import com.test_case.app.model.entity.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametersRepository extends JpaRepository<Parameters, Long> , JpaSpecificationExecutor<Parameters> {

    List<Parameters> findByKeyAndValue(String key);
}
