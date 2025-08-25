package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;


@Repository
public interface UserRepo extends JpaRepository<Users,Long>{
	Users findByusername(String username);
	Optional<Users> findByUsername(String username);
	Optional<Users> findById(Long userId); 

}
