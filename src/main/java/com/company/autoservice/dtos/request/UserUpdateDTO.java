package com.company.autoservice.dtos.request;

import com.company.autoservice.enums.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDTO {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private Long mediaID;
    private Long credits;
    private Long availableTokens;
}