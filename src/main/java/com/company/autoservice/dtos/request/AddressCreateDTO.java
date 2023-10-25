package com.company.autoservice.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressCreateDTO {
    @NotBlank(message = "City must not be blank")
    private String city;

    @NotBlank(message = "Street must not be blank")
    private String street;

    @NotBlank(message = "House must not be blank")
    private String house;
}