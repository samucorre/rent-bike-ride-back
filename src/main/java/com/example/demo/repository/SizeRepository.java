package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Size;

public interface SizeRepository extends JpaRepository<Size, Integer>, JpaSpecificationExecutor<Size> {

}
