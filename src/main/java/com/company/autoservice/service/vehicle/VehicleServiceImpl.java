package com.company.autoservice.service.vehicle;


import com.company.autoservice.dtos.request.VehicleCreateDTO;
import com.company.autoservice.dtos.response.VehicleResponseDTO;
import com.company.autoservice.entity.Vehicle;
import com.company.autoservice.exception.DuplicateValueException;
import com.company.autoservice.exception.ItemNotFoundException;
import com.company.autoservice.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    @Override
    public VehicleResponseDTO create(VehicleCreateDTO vehicleCreateDTO) {
        checkVehicleUnique(vehicleCreateDTO.getVIN());
        Vehicle mappedVehicle = modelMapper.map(vehicleCreateDTO, Vehicle.class);
        Vehicle savedVehicle = vehicleRepository.save(mappedVehicle);
        return modelMapper.map(savedVehicle, VehicleResponseDTO.class);
    }

    @Override
    public Page<VehicleResponseDTO> getAll(Integer page, Integer size) {
        return vehicleRepository.findAll(PageRequest.of(page, size))
                .map(this::toDTO);
    }

    @Override
    public VehicleResponseDTO getByID(Long vehicleID) {
        return modelMapper.map(getVehicleByID(vehicleID), VehicleResponseDTO.class);
    }

    private VehicleResponseDTO toDTO(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleResponseDTO.class);
    }

    private void checkVehicleUnique(String VIN) {
        if (vehicleRepository.existsByVIN(VIN))
            throw new DuplicateValueException("Vehicle exists by VIN: " + VIN);
    }

    private Vehicle getVehicleByID(Long vehicleID) {
        return vehicleRepository.findById(vehicleID).orElseThrow(
                () -> new ItemNotFoundException("Vehicle not found with ID: " + vehicleID)
        );
    }

}