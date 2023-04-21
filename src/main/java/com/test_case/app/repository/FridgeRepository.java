package com.test_case.app.repository;

import com.test_case.app.model.entity.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FridgeRepository extends JpaRepository<Fridge, Long> {

    public List<Fridge> findByName(String name);

}
