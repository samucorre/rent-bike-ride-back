package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Bike;

public interface BikeRepository extends JpaRepository<Bike, Integer>, JpaSpecificationExecutor<Bike> {

}
