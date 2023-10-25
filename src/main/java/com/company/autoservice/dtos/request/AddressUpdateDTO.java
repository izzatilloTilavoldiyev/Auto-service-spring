package com.company.autoservice.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressUpdateDTO {
    private String city;
    private String street;
    private String house;
}