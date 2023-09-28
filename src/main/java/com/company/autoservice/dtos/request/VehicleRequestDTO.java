package com.company.autoservice.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleRequestDTO {

    @NotBlank(message = "VIN must not be blank")
    private String VIN;

    @NotBlank(message = "Maker must not be blank")
    private String maker;

    @NotBlank(message = "Model must not be blank")
    private String model;

    @NotBlank(message = "Body type must not be blank")
    private String bodyType;

    @NotBlank(message = "Fuel type must not be blank")
    private String fuelType;

    @NotNull(message = "Horsepower must not be null")
    private Integer horsepower;

    @NotNull(message = "CMM must not be null")
    private Long CMM;

    @NotBlank(message = "Description must not be blank")
    private String description;

}