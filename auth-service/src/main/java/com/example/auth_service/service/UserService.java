package com.example.auth_service.service;

import com.example.auth_service.model.UserInfo;
import com.example.auth_service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserInfo insertUsers(UserInfo users) throws Exception
    {
        UserInfo user =  userRepo.save(users);
        return user;
    }
}
