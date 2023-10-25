package com.company.autoservice.service.vehicle;


import com.company.autoservice.dtos.request.VehicleRequestDTO;
import com.company.autoservice.dtos.request.VehicleUpdateDTO;
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
    public VehicleResponseDTO create(VehicleRequestDTO vehicleRequestDTO) {
        checkVehicleUnique(vehicleRequestDTO.getVIN());
        Vehicle mappedVehicle = modelMapper.map(vehicleRequestDTO, Vehicle.class);
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

    @Override
    public VehicleResponseDTO update(Long vehicleID, VehicleUpdateDTO vehicleUpdateDTO) {
        Vehicle vehicle = getVehicleByID(vehicleID);
        checkVehicleUnique(vehicleUpdateDTO.getVIN());
        modelMapper.map(vehicleUpdateDTO, vehicle);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(savedVehicle, VehicleResponseDTO.class);
    }

    @Override
    public void delete(Long ID) {
        if (!vehicleRepository.existsById(ID))
            throw new ItemNotFoundException("Vehicle not found with ID: " + ID);
        vehicleRepository.deleteById(ID);
    }

    @Override
    public void deleteSelectedVehicles(List<Long> vehicleIDs) {
        for (Long vehicleID : vehicleIDs) {
            delete(vehicleID);
        }
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