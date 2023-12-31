package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Toppings;

@Repository
public interface ToppingsRepository extends JpaRepository<Toppings, Integer> {

}
