package com.company.autoservice.service.auth;

import com.company.autoservice.dtos.request.LoginDTO;
import com.company.autoservice.dtos.response.TokenDTO;

public interface AuthService {
    TokenDTO login(LoginDTO loginDTO);

}