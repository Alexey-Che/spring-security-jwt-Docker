package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.config.jwt.JwtProvider;
import com.example.springsecurityjwt.entity.UserEntity;
import com.example.springsecurityjwt.entity.request.AuthRequest;
import com.example.springsecurityjwt.entity.request.RegistrationRequest;
import com.example.springsecurityjwt.entity.response.AuthResponse;
import com.example.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")   //регистрация в БД нового пользователя
    @ResponseBody
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(registrationRequest.getPassword());
        userEntity.setLogin(registrationRequest.getLogin());
        userService.saveUser(userEntity);
        return "OK";
    }

    @PostMapping("/auth")  //получение jwt токена зарегестрированным пользователем
    @ResponseBody
    public AuthResponse auth(@RequestBody @Valid AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.createToken(userEntity.getLogin());
        return new AuthResponse(token);
    }
}
