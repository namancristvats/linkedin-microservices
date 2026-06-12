package com.ncv.LinkedInProject.user_service.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String name,email,password;
}
