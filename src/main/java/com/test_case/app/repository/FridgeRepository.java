package com.test_case.app.repository;

import com.test_case.app.model.entity.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FridgeRepository extends JpaRepository<Fridge, Long> {

    List<Fridge> findByName(String name);
}
