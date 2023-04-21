package com.test_case.app.repository.model_repository;

import com.test_case.app.model.entity.entity_model.HooverModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface HooverModelRepository extends CrudRepository<HooverModel, Long>  , JpaSpecificationExecutor<HooverModel> {
}
