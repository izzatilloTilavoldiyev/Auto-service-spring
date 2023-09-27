package com.company.autoservice.service.vehicle;

import com.company.autoservice.dtos.request.VehicleCreateDTO;
import com.company.autoservice.dtos.response.VehicleResponseDTO;
import org.springframework.data.domain.Page;

public interface VehicleService {

    VehicleResponseDTO create(VehicleCreateDTO vehicleCreateDTO);

    Page<VehicleResponseDTO> getAll(Integer page, Integer size);

    VehicleResponseDTO getByID(Long vehicleID);
}
