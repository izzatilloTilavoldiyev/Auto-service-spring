package com.company.autoservice.service.auth;


import com.company.autoservice.config.jwt.JwtService;
import com.company.autoservice.dtos.request.LoginDTO;
import com.company.autoservice.dtos.response.TokenDTO;
import com.company.autoservice.entity.User;
import com.company.autoservice.exception.ItemNotFoundException;
import com.company.autoservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
        User user = getUserByEmail(loginDTO.getEmail(), loginDTO.getPassword());
        return TokenDTO.builder()
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }

    private User getUserByEmail(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ItemNotFoundException("User not found with Email: " + email)
        );
        if (!user.getPassword().equals(password))
            throw new ItemNotFoundException("User not found with Password: " + password);
        else return user;
    }

}