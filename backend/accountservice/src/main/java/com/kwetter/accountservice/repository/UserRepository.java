package com.kwetter.accountservice.repository;

import javax.transaction.Transactional;

import com.kwetter.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Integer> {

  boolean existsByUsername(String username);

  Account findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);

}
