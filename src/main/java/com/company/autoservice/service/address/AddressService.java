package com.company.autoservice.service.address;

import com.company.autoservice.dtos.request.AddressRequestDTO;
import com.company.autoservice.dtos.response.AddressResponseDTO;
import com.company.autoservice.entity.Address;

public interface AddressService {

    AddressResponseDTO create(AddressRequestDTO addressRequestDTO);

    Address getByID(Long addressID);

    AddressResponseDTO update(Long addressID, AddressResponseDTO addressResponseDTO);

    void delete(Long addressID);
}
