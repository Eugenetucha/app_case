package com.test_case.app.repository;

import com.test_case.app.model.entity.Line;
import com.test_case.app.model.entity.Model;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends CrudRepository<Model, Long>, JpaSpecificationExecutor<Model> {

}
