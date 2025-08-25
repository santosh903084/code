package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.enums.ElectionStatus;
import com.example.demo.model.Election;

 
@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {
	//List<Election> findByIsActiveTrue();

	List<Election> findByStatus(ElectionStatus ongoing);
}
 
