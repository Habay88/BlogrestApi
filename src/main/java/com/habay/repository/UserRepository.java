package com.habay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habay.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

 Optional <User>findByEmail(String email);
 Optional<User> findByUsernameOrEmail(String username, String eamil	);
 Optional <User> findByUsername(String username);
 Boolean existsByUsername(String username);
 Boolean existsByEmail(String email);

}
