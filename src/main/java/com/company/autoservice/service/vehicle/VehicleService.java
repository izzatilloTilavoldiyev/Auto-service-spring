package com.company.autoservice.service.vehicle;

import com.company.autoservice.dtos.request.VehicleRequestDTO;
import com.company.autoservice.dtos.request.VehicleUpdateDTO;
import com.company.autoservice.dtos.response.VehicleResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VehicleService {

    VehicleResponseDTO create(VehicleRequestDTO vehicleRequestDTO);

    Page<VehicleResponseDTO> getAll(Integer page, Integer size);

    VehicleResponseDTO getByID(Long vehicleID);

    VehicleResponseDTO update(Long ID, VehicleUpdateDTO vehicleUpdateDTO);

    void delete(Long ID);

    void deleteSelectedVehicles(List<Long> vehicleIDs);
}
