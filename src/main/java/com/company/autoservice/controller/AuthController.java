package com.company.autoservice.controller;


import com.company.autoservice.dtos.request.LoginDTO;
import com.company.autoservice.dtos.response.TokenDTO;
import com.company.autoservice.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(
            description = "POST endpoint to user login",
            summary = "login"
    )
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(
            @RequestBody LoginDTO loginDTO
    ) {
        TokenDTO token = authService.login(loginDTO);
        return ResponseEntity.ok(token);
    }

}