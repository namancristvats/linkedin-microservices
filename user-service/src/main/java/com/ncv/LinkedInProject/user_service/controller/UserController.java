package com.ncv.LinkedInProject.user_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ncv.LinkedInProject.user_service.dto.LoginRequestDto;
import com.ncv.LinkedInProject.user_service.dto.SignupRequestDto;
import com.ncv.LinkedInProject.user_service.dto.UserDto;
import com.ncv.LinkedInProject.user_service.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto){
        UserDto userDto=authService.signup(signupRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){
        String token=authService.login(loginRequestDto);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
