package com.company.autoservice.entity;


import com.company.autoservice.enums.Status;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    @Column(nullable = false)
    private String contactPerson;

    @Column(nullable = false, unique = true)
    private String contactPhone;

    private Long credits;

    private Long availableTokens;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> employees;

}

@Entity(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Address extends BaseEntity{
    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String house;
}