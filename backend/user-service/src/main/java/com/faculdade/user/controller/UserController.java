package com.faculdade.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.user.dto.LoginRequest;
import com.faculdade.user.dto.LoginResponse;
import com.faculdade.user.dto.UserRequest;
import com.faculdade.user.dto.UserResponse;
import com.faculdade.user.entity.User;
import com.faculdade.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public UserResponse salvar(
            @RequestBody UserRequest request) {

        return service.salvar(request);

    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return service.login(request);

    }

    @GetMapping
    public List<User> listar() {

        return service.listar();

    }

}