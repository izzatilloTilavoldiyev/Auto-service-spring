package com.company.autoservice.entity;


import lombok.*;
import jakarta.persistence.*;

@Entity(name = "car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String VIN;

    @Column(nullable = false)
    private String maker;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String bodyType;

    @Column(nullable = false)
    private String fuelType;

    @Column(nullable = false)
    private Integer horsepower;

    @Column(nullable = false)
    private Integer CMM;

    @Column(columnDefinition = "text")
    private String description;

}