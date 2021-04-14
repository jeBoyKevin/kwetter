package com.kwetter.accountservice.repository;

import javax.transaction.Transactional;

import com.kwetter.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Integer> {

  boolean existsByEmail(String email);

  Account findByEmail(String email);

  Account findById(int id);

  @Transactional
  void deleteByEmail(String email);

}
