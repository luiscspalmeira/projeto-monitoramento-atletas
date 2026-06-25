package com.faculdade.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faculdade.user.entity.User;
import com.faculdade.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User salvar(User user) {
        return repository.save(user);
    }

    public List<User> listar() {
        return repository.findAll();
    }
}