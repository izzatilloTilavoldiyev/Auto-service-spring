package com.company.autoservice.controller;


import com.company.autoservice.dtos.request.VehicleCreateDTO;
import com.company.autoservice.dtos.response.VehicleResponseDTO;
import com.company.autoservice.service.vehicle.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Operation(
            description = "POST endpoint to create vehicle",
            summary = "create"
    )
    @PostMapping
    public ResponseEntity<VehicleResponseDTO> create(
            @Valid @RequestBody VehicleCreateDTO vehicleCreateDTO
    ) {
        VehicleResponseDTO createdVehicle = vehicleService.create(vehicleCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }


    @Operation(
            description = "GET endpoint to get all vehicle pages, you should give page and size",
            summary = "get all pages"
    )
    @GetMapping("/all-page")
    public ResponseEntity<Page<VehicleResponseDTO>> getAllPages(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "9") Integer size
    ) {
        Page<VehicleResponseDTO> allPage = vehicleService.getAll(page, size);
        return ResponseEntity.ok(allPage);
    }


    @Operation(
            description = "GET endpoint to get vehicle by ID",
            summary = "get by ID"
    )
    @GetMapping("/{ID}")
    public ResponseEntity<VehicleResponseDTO> getByID(
            @PathVariable Long ID
    ) {
        VehicleResponseDTO vehicleResponse = vehicleService.getByID(ID);
        return ResponseEntity.ok(vehicleResponse);
    }
}