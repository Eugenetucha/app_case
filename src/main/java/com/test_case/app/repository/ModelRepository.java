package com.test_case.app.repository;

import com.test_case.app.model.entity.Model;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


public interface ModelRepository extends CrudRepository<Model, Long>, JpaSpecificationExecutor<FridgeModel> {

}
