package com.kwetter.accountservice.service;

import com.kwetter.accountservice.exception.CustomException;
import com.kwetter.accountservice.model.Account;
import com.kwetter.accountservice.model.SigninReturnObject;
import com.kwetter.accountservice.repository.UserRepository;
import com.kwetter.accountservice.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public SigninReturnObject signin(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      String token = jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
      int user_id = userRepository.findByUsername(username).getId();
      SigninReturnObject returnObject = new SigninReturnObject();
      returnObject.setToken(token);
      returnObject.setUser_id(user_id);

      return returnObject;
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public SigninReturnObject signup(Account account) {
    if (!userRepository.existsByUsername(account.getUsername())) {
      account.setPassword(passwordEncoder.encode(account.getPassword()));
      userRepository.save(account);
      String token = jwtTokenProvider.createToken(account.getUsername(), account.getRoles());
      int user_id = userRepository.findByUsername(account.getUsername()).getId();
      SigninReturnObject returnObject = new SigninReturnObject();
      returnObject.setToken(token);
      returnObject.setUser_id(user_id);
      return returnObject;
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public Account search(String username) {
    Account account = userRepository.findByUsername(username);
    if (account == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return account;
  }

  public Account whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public List<Account> getUsernames(List<Integer> ids) {
    List<Account> allAccounts = new ArrayList<>();
    for (int i=0; i < ids.size(); i++){
      userRepository.findById(ids.get(i)).ifPresent(allAccounts::add);
    }
      return allAccounts;
  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
  }


}
