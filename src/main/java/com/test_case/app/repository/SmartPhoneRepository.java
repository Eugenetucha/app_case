package com.test_case.app.repository;

import com.test_case.app.model.entity.SmartPhone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SmartPhoneRepository extends CrudRepository<SmartPhone, Long> {
    @Query(
            value = "SELECT * FROM smartphone u WHERE u.name ilike ?1",
            nativeQuery = true)
    public List<SmartPhone> findByName(String name);
}
