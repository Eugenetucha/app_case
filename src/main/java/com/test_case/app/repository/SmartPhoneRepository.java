package com.test_case.app.repository;

import com.test_case.app.model.entity.SmartPhone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SmartPhoneRepository extends CrudRepository<SmartPhone, Long> {
    @Query(
            value = "SELECT * FROM smartphone u WHERE u.name ilike ?1",
            nativeQuery = true)
    public SmartPhone findByName(String name);
}
