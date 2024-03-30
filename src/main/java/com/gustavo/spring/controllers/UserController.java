package com.gustavo.spring.controllers;

import com.gustavo.spring.domain.User;
import com.gustavo.spring.useCases.user.GetAllUsersUsecase;
import com.gustavo.spring.useCases.user.GetUserByIdUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private GetAllUsersUsecase getAllOrdersUsecase;

    @Autowired
    private GetUserByIdUsecase getUserByIdUsecase;

    @GetMapping
    public List<User> getAllUser() {
        return getAllOrdersUsecase.execute();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        var userOptional = getUserByIdUsecase.execute(id);

        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
