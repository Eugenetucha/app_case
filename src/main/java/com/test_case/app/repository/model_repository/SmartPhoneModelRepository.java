package com.test_case.app.repository.model_repository;

import com.test_case.app.model.entity.entity_model.SmartPhoneModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SmartPhoneModelRepository extends CrudRepository<SmartPhoneModel, Long>  , JpaSpecificationExecutor<SmartPhoneModel> {
}
