package com.example.atividades.atividade10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void getUserInfoReturnsNullForAnyUserId() {
        User user = userService.getUserInfo(1);
        assertNull(user);

        user = userService.getUserInfo(999);
        assertNull(user);
    }
}
