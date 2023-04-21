package com.test_case.app.repository;

import com.test_case.app.model.entity.SmartPhone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SmartPhoneRepository extends CrudRepository<SmartPhone, Long> {
    @Query(
            value = "SELECT u FROM SmartPhone u WHERE u.name like :name")
    public List<SmartPhone> findByName(@Param("name")String name);
}
