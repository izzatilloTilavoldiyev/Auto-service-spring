package com.company.autoservice.service.address;


import com.company.autoservice.dtos.request.AddressCreateDTO;
import com.company.autoservice.dtos.request.AddressUpdateDTO;
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
    public AddressResponseDTO create(AddressCreateDTO addressCreateDTO) {
        Address mappedAddress = modelMapper.map(addressCreateDTO, Address.class);
        Address savedAddress = addressRepository.save(mappedAddress);
        return modelMapper.map(savedAddress, AddressResponseDTO.class);
    }

    @Override
    public Address getByID(Long addressID) {
        return getAddressByID(addressID);
    }

    @Override
    public AddressResponseDTO update(Long addressID, AddressUpdateDTO addressUpdateDTO) {
        Address address = getAddressByID(addressID);
        modelMapper.map(addressUpdateDTO, address);
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