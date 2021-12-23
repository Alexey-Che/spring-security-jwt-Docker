package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.config.jwt.JwtProvider;
import com.example.springsecurityjwt.dto.request.AuthRequestDto;
import com.example.springsecurityjwt.dto.request.RegistrationRequestDto;
import com.example.springsecurityjwt.dto.response.AuthResponseDto;
import com.example.springsecurityjwt.entity.RoleEntity;
import com.example.springsecurityjwt.entity.UserEntity;
import com.example.springsecurityjwt.repository.RoleEntityRepository;
import com.example.springsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/register")   //регистрация в БД нового пользователя
    @ResponseBody
    public String registerUser(@RequestBody @Valid RegistrationRequestDto registrationRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(registrationRequest.getPassword());
        userEntity.setLogin(registrationRequest.getLogin());
        if (userService.findByLogin(registrationRequest.getLogin()) == null) {
            userService.saveUser(userEntity);
        }
        return "OK";
    }

    @PostMapping("/auth")  //получение jwt токена зарегистрированным пользователем
    @ResponseBody
    public AuthResponseDto auth(@RequestBody @Valid AuthRequestDto request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.createToken(userEntity.getLogin());
        return new AuthResponseDto(token);
    }
}
