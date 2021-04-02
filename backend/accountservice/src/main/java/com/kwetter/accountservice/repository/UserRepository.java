package com.kwetter.accountservice.repository;

import javax.transaction.Transactional;

import com.kwetter.accountservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String username);

  User findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);

}