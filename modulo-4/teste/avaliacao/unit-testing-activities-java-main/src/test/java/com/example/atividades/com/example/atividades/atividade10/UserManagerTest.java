package com.example.atividades.atividade10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.github.javafaker.Faker;

class UserManagerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserManager userManager;

    
    private User user;
    private Faker faker;
    

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userManager = new UserManager(userService);
        faker = new Faker();
        
        user = new User(faker.name().fullName(), faker.internet().emailAddress());
    }

    @Test
    void fetchUser_InfoReturnsUserWhenUserExists() {
        when(userService.getUserInfo(1)).thenReturn(user);

        User actualUser = userManager.fetchUserInfo(1);

        assertNotNull(actualUser);
        assertEquals(user.getName(), actualUser.getName());
        assertEquals(user.getEmail(), actualUser.getEmail());
    }

    @Test
    void fetchUser_InfoThrowsExceptionWhenUserNotFound_ReturnRuntimeException() {
        when(userService.getUserInfo(anyInt())).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userManager.fetchUserInfo(1);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void fetchUser_InfoThrowsExceptionWhenUserIdIsInvalid_ReturnRuntimeException() {
        when(userService.getUserInfo(-1)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userManager.fetchUserInfo(-1);
        });

        assertEquals("User not found", exception.getMessage());
    }
}
