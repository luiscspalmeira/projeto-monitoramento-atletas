package com.faculdade.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.user.entity.User;

public interface UserRepository
extends JpaRepository<User, Long> {
}