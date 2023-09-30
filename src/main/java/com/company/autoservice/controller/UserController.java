package com.company.autoservice.controller;

import com.company.autoservice.dtos.request.UserCreateDTO;
import com.company.autoservice.dtos.response.UserResponseDTO;
import com.company.autoservice.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(
            description = "POST endpoint to create user" +
                    "201 = successfully created" +
                    "400 = Bad request" +
                    "409 = Duplicate value exception",
            summary = "Create user"
    )
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


    @Operation(
            description = "GET endpoint to get all user pages",
            summary = "get all pages"
    )
    @GetMapping("/all-pages")
    public ResponseEntity<Page<UserResponseDTO>> getAllPages(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "12") Integer size
    ) {
        Page<UserResponseDTO> allUserPages = userService.getAllPages(page, size);
        return ResponseEntity.ok(allUserPages);
    }


    @Operation(
            description = "GET endpoint to get all user pages by companyID",
            summary = "get all by companyID"
    )
    @GetMapping("/company-users")
    public ResponseEntity<Page<UserResponseDTO>> getAllByCompanyID(
            @RequestParam Long companyID,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "12") Integer size
    ) {
        Page<UserResponseDTO> allByCompanyID = userService.getAllByCompanyID(companyID, page, size);
        return ResponseEntity.ok(allByCompanyID);
    }

/*    @Operation(
            description = "GET endpoint to get all blocked user pages",
            summary = "get all pages"
    )
    @GetMapping("/all-blocked")
    public ResponseEntity<Page<UserResponseDTO>> getAllBlocked(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "12") Integer size
    ) {
        Page<UserResponseDTO> allBlocked = userService.getAllBlocked(page, size);
        return ResponseEntity.ok(allBlocked);
    }*/

    @Operation(
            description = "PUT endpoint to block user",
            summary = "block"
    )
    @PutMapping("/{userID}")
    public ResponseEntity<String> block(
            @PathVariable Long userID
    ) {
        userService.block(userID);
        return ResponseEntity.ok("Successfully blocked!");
    }


    @Operation(
            description = "PUT endpoint to unblock user",
            summary = "unblock"
    )
    @PutMapping("/{userID}")
    public ResponseEntity<String> unblock(
            @PathVariable Long userID
    ) {
        userService.unblock(userID);
        return ResponseEntity.ok("Successfully unblocked!");
    }


    @Operation(
            description = "DELETE endpoint to delete user by ID",
            summary = "delete"
    )
    @DeleteMapping("/{userID}")
    public ResponseEntity<String> delete(
            @PathVariable Long userID
    ) {
        userService.delete(userID);
        return ResponseEntity.ok("Successfully deleted!");
    }
}