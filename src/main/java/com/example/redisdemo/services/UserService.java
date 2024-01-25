package com.example.redisdemo.services;

import com.example.redisdemo.Dto.UserDto;
import com.example.redisdemo.model.User;
import org.springframework.data.domain.Page;


import java.util.List;

public interface UserService {
    Page<UserDto> getAllUsers(int page, int size);
    User getUserById(Long id);
    User saveUser(User user);

    User updateUser(UserDto userDto, Long id);
    void deleteUser(Long id);
}
