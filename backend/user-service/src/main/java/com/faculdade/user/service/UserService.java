package com.faculdade.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faculdade.user.dto.LoginRequest;
import com.faculdade.user.dto.LoginResponse;
import com.faculdade.user.dto.UserRequest;
import com.faculdade.user.dto.UserResponse;
import com.faculdade.user.entity.User;
import com.faculdade.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponse salvar(UserRequest request) {

        User user = new User();

        user.setNome(request.getNome());
        user.setEmail(request.getEmail());
        user.setSenha(request.getSenha());
        user.setTipoUsuario(request.getPerfil());
        user.setPcd(request.isPcd());

        user = repository.save(user);

        return converterParaResponse(user);
    }

    public List<User> listar() {

        return repository.findAll();

    }

    public LoginResponse login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado."));

        if (!user.getSenha().equals(request.getSenha())) {
            throw new RuntimeException("Senha inválida.");
        }

        LoginResponse response = new LoginResponse();

        response.setId(user.getId());
        response.setNome(user.getNome());
        response.setEmail(user.getEmail());
        response.setPerfil(user.getTipoUsuario());
        response.setPcd(Boolean.TRUE.equals(user.getPcd()));

        return response;
    }

    private UserResponse converterParaResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setNome(user.getNome());
        response.setEmail(user.getEmail());
        response.setPerfil(user.getTipoUsuario());
        response.setPcd(Boolean.TRUE.equals(user.getPcd()));

        return response;
    }

}