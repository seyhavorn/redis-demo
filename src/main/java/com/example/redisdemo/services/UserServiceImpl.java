package com.example.redisdemo.services;

import com.example.redisdemo.Dto.UserDto;
import com.example.redisdemo.mapper.UserMapper;
import com.example.redisdemo.model.User;
import com.example.redisdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable("users")
    public Page<UserDto> getAllUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageRequest);

        return new PageImpl<>(users.getContent().stream().map(user -> userMapper.userToUserDto(user)).collect(Collectors.toList()), pageRequest, users.getTotalElements());
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        System.out.println("Getting user by id from DB");
        return userRepository.findById(id).orElse(null);
    }

    @Override
//    @CacheEvict(value = "users", allEntries = true)
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(value = "users", key = "#id")
    public User updateUser(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElse(null);
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return user;
    }


    @Override
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
