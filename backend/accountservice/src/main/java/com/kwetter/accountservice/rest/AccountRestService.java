package com.kwetter.accountservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.accountservice.manager.AccountManager;
import com.kwetter.accountservice.models.returnModels.AccountReturnModel;
import com.kwetter.accountservice.models.submitModels.AccountSubmitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

@RestController
public class AccountRestService {

    @Autowired
    private AccountManager manager = new AccountManager();

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value =  "/register",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody String json) throws JsonProcessingException {
        AccountSubmitModel submitModel = objectMapper.readValue(json, AccountSubmitModel.class);

        String username = submitModel.getUsername();
        String password = submitModel.getPassword();
        String sha256 = sha256Hex(password);

        if (username.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        AccountReturnModel returnModel = manager.register(username, sha256);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value = "/login",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody String json) throws JsonProcessingException {
        AccountSubmitModel submitModel = objectMapper.readValue(json, AccountSubmitModel.class);

        String username = submitModel.getUsername();
        String password = submitModel.getPassword();
        String sha256 = sha256Hex(password);

        if (username.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        AccountReturnModel returnModel = manager.login(username, sha256);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(objectMapper.writeValueAsString(returnModel));
        }
    }

}
