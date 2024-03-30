package com.gustavo.spring.useCases.user;

import com.gustavo.spring.domain.User;
import com.gustavo.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetUserByIdUsecase {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> execute(UUID id) {
        return userRepository.findById(id);
    }

}
