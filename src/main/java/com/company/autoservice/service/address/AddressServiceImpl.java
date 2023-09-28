package com.company.autoservice.service.address;


import com.company.autoservice.dtos.request.AddressRequestDTO;
import com.company.autoservice.dtos.response.AddressResponseDTO;
import com.company.autoservice.entity.Address;
import com.company.autoservice.exception.ItemNotFoundException;
import com.company.autoservice.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public AddressResponseDTO create(AddressRequestDTO addressRequestDTO) {
        Address mappedAddress = modelMapper.map(addressRequestDTO, Address.class);
        Address savedAddress = addressRepository.save(mappedAddress);
        return modelMapper.map(savedAddress, AddressResponseDTO.class);
    }

    @Override
    public AddressResponseDTO getByID(Long addressID) {
        return modelMapper.map(getAddressByID(addressID), AddressResponseDTO.class);
    }

    @Override
    public AddressResponseDTO update(Long addressID, AddressRequestDTO addressRequestDTO) {
        Address address = getAddressByID(addressID);
        modelMapper.map(addressRequestDTO, address);
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressResponseDTO.class);
    }

    @Override
    public void delete(Long addressID) {
        if (!addressRepository.existsById(addressID))
            throw new ItemNotFoundException("Address not found with ID: " + addressID);
        addressRepository.deleteById(addressID);
    }

    private Address getAddressByID(Long addressID) {
        return addressRepository.findById(addressID).orElseThrow(
                () -> new ItemNotFoundException("Address not found with ID: " + addressID)
        );
    }
}