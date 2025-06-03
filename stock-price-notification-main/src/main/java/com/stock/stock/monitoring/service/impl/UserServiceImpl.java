package com.stock.stock.monitoring.service.impl;

import com.stock.stock.monitoring.entity.User;
import com.stock.stock.monitoring.reository.UserRepository;
import com.stock.stock.monitoring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
