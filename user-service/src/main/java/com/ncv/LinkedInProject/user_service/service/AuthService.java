package com.ncv.LinkedInProject.user_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.ncv.LinkedInProject.user_service.dto.LoginRequestDto;
import com.ncv.LinkedInProject.user_service.dto.SignupRequestDto;
import com.ncv.LinkedInProject.user_service.dto.UserDto;
import com.ncv.LinkedInProject.user_service.entity.User;
import com.ncv.LinkedInProject.user_service.exceptions.BadRequestException;
import com.ncv.LinkedInProject.user_service.repository.UserRepository;
import com.ncv.LinkedInProject.user_service.util.BCrypt;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signup(SignupRequestDto signupRequestDto) {
        log.info("Signup a user with email:{}",signupRequestDto.getEmail());
        boolean exists=userRepository.existsByEmail(signupRequestDto.getEmail());
        if(exists){
            throw new BadRequestException("User with given email already exists");
        }
        User user=modelMapper.map(signupRequestDto,User.class);
        user.setPassword(BCrypt.hash(signupRequestDto.getPassword()));
        user=userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto){
        log.info("Login request for user with email: {}", loginRequestDto.getEmail());
        User user=userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()->new BadRequestException("Incorrect email or password!!!"));

        boolean isPasswordMatch=BCrypt.match(loginRequestDto.getPassword(),user.getPassword());
        if(!isPasswordMatch){
            throw new BadRequestException("Incorrect Email or Password!");
        }
        return jwtService.generateAccessToken(user);
    }

}
