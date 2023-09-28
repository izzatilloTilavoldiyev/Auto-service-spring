package com.company.autoservice.service.address;

import com.company.autoservice.dtos.request.AddressRequestDTO;
import com.company.autoservice.dtos.response.AddressResponseDTO;

public interface AddressService {

    AddressResponseDTO create(AddressRequestDTO addressRequestDTO);

    AddressResponseDTO getByID(Long addressID);

    AddressResponseDTO update(Long addressID, AddressRequestDTO addressRequestDTO);

    void delete(Long addressID);
}
