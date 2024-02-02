package com.example.redisdemo.controller;

import com.example.redisdemo.Dto.UserDto;
import com.example.redisdemo.model.User;
import com.example.redisdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableCaching
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserDto> getAllUsers(@RequestParam(name = "page",defaultValue = "0") int page,
                                     @RequestParam(name = "size",defaultValue = "10") int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody UserDto user, @PathVariable Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
