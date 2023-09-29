package com.company.autoservice.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyCreateDTO {
    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotNull(message = "Address ID must not be null")
    private Long addressID;

    @NotBlank(message = "Contact person must not be blank")
    private String contactPerson;

    @NotBlank(message = "Contact phone must not be blank")
    private String contactPhone;

    private Long credits;

    private Long availableTokens;
}