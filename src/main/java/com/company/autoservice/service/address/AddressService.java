package com.company.autoservice.service.address;

import com.company.autoservice.dtos.request.AddressCreateDTO;
import com.company.autoservice.dtos.request.AddressUpdateDTO;
import com.company.autoservice.dtos.response.AddressResponseDTO;
import com.company.autoservice.entity.Address;

public interface AddressService {

    AddressResponseDTO create(AddressCreateDTO addressCreateDTO);

    Address getByID(Long addressID);

    AddressResponseDTO update(Long addressID, AddressUpdateDTO addressUpdateDTO);

    void delete(Long addressID);
}
