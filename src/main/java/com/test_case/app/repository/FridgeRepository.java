package com.test_case.app.repository;

import com.test_case.app.model.entity.Fridge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FridgeRepository extends CrudRepository<Fridge, Long> {

    @Query(
            value = "SELECT u FROM Fridge u WHERE u.name like ?1")
    public List<Fridge> findByName(String name);

}
