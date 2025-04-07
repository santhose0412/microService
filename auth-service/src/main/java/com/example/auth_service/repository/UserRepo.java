package com.example.auth_service.repository;

import com.example.auth_service.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByUsername(String username);
}
