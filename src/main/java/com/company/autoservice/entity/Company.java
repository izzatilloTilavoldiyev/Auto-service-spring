package com.company.autoservice.entity;

import com.company.autoservice.enums.Status;
import lombok.*;
import jakarta.persistence.*;

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
}

