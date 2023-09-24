package com.company.autoservice.controller;


import com.company.autoservice.dtos.request.UserCreateDTO;
import com.company.autoservice.dtos.response.UserResponseDTO;
import com.company.autoservice.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(
            description = "POST endpoint to create user\n" +
                    "201 = successfully created\n" +
                    "400 = Bad request\n" +
                    "409 = Duplicate value exception",
            summary = "Create user"
    )
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<UserResponseDTO> addEmployee(
            @Valid @RequestBody UserCreateDTO userCreateDTO
    ) {
        UserResponseDTO userResponseDTO = userService.addEmployee(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @Operation(
            description = "GET endpoint to get user by ID",
            summary = "Get by ID"
    )
    @GetMapping("/{userID}")
    public ResponseEntity<UserResponseDTO> getByID(
            @PathVariable Long userID
    ) {
        UserResponseDTO user = userService.getByID(userID);
        return ResponseEntity.ok(user);
    }
}