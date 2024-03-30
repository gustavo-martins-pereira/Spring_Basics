package com.gustavo.spring.useCases.user;

import com.gustavo.spring.domain.User;
import com.gustavo.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsersUsecase {

    @Autowired
    private UserRepository userRepository;

    public List<User> execute() {
        return userRepository.findAll();
    }

}
