package com.company.autoservice.service.auth;


import com.company.autoservice.config.jwt.JwtService;
import com.company.autoservice.dtos.request.LoginDTO;
import com.company.autoservice.dtos.request.TokenRequestDTO;
import com.company.autoservice.dtos.response.TokenDTO;
import com.company.autoservice.entity.User;
import com.company.autoservice.exception.ItemNotFoundException;
import com.company.autoservice.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDTO.getEmail(),
//                        loginDTO.getPassword()
//                )
//        );
// this method use when i save password encoded.d
        User user = checkUserExists(loginDTO.getEmail(), loginDTO.getPassword());
        return jwtService.generateToken(user.getEmail());
    }

    @Override
    public TokenDTO accessToken(TokenRequestDTO tokenRequestDTO) {
        User user = checkUserExists(tokenRequestDTO.email(), tokenRequestDTO.password());
        return jwtService.generateToken(user.getEmail());
    }

    @Override
    public TokenDTO refreshToken(@NotNull String refreshToken) {
        if (jwtService.isTokenExpired(refreshToken))
            throw new CredentialsExpiredException("Token is invalid");

        String email = jwtService.getUsername(refreshToken);
        getUserByEmail(email);
        return jwtService.generateToken(email);
    }

    private User checkUserExists(String email, String password) {
        User user = getUserByEmail(email);
        if (!password.equals(user.getPassword()))
            throw new ItemNotFoundException("User not found with Password: " + password);
        return user;
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ItemNotFoundException("User not found with Email: " + email)
        );
    }

}