package com.company.autoservice.dtos.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleUpdateDTO {

    private String VIN;
    private String maker;
    private String model;
    private String bodyType;
    private String fuelType;
    private Integer horsepower;
    private Long CMM;
    private String description;
    private LocalDateTime createdDate;

}