package com.company.autoservice.dtos.response;

import com.company.autoservice.enums.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private Long companyID;
    private Long mediaID;
    private Long credits;
    private Long availableTokens;
    private Status status;
}