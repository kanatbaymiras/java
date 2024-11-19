package org.example.lab5.controller;

import org.example.lab5.DTO.RegistrationDto;
import org.example.lab5.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RegistrationController {
    UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        RegistrationDto registrationDto = new RegistrationDto(username, password);
        userService.createUser(registrationDto);
        return "redirect:/login";
    }
}
