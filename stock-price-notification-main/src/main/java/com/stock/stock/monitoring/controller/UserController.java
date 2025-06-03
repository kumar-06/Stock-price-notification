package com.stock.stock.monitoring.controller;

import com.stock.stock.monitoring.entity.User;
import com.stock.stock.monitoring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/create")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }
}
