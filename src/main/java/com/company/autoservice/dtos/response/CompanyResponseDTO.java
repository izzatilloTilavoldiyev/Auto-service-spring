package com.company.autoservice.dtos.response;

import com.company.autoservice.entity.Address;
import com.company.autoservice.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponseDTO {
    private String name;
    private String email;
    private Long addressID;
    private String contactPerson;
    private String contactPhone;
    private Long credits;
    private Long availableTokens;
    private Status status;
}