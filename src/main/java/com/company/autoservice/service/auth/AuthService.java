package com.company.autoservice.service.auth;

import com.company.autoservice.dtos.request.LoginDTO;
import com.company.autoservice.dtos.request.TokenRequestDTO;
import com.company.autoservice.dtos.response.TokenDTO;

import java.security.Principal;

public interface AuthService {
    TokenDTO login(LoginDTO loginDTO);

    TokenDTO refreshToken(String refreshToken);

}