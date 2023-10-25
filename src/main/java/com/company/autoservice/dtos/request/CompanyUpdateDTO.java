package com.company.autoservice.dtos.request;

import com.company.autoservice.enums.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyUpdateDTO {
    private String name;
    private String email;
    private Long addressID;
    private String contactPerson;
    private String contactPhone;
    private Long credits;
    private Long availableTokens;
    private Status status;
}