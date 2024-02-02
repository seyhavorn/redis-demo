package com.example.redisdemo;

import com.example.redisdemo.model.User;
import com.example.redisdemo.services.UserService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        createUsers();
    }

    private void createUsers() {
        Faker faker = new Faker();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setUsername(faker.name().username());
            user.setEmail(faker.internet().emailAddress());
            userService.saveUser(user);
        }
        System.out.println("Initial data completed");
    }
}
