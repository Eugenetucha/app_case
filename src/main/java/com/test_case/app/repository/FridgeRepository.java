package com.test_case.app.repository;

import com.test_case.app.model.entity.Fridge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FridgeRepository extends CrudRepository<Fridge, Long> {
    @Query(
            value = "SELECT * FROM fridge u WHERE u.name ilike ?1",
            nativeQuery = true)
    public List<Fridge> findByName(String name);

}
