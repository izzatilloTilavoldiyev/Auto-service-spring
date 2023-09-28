package com.company.autoservice.controller;

import com.company.autoservice.dtos.request.AddressRequestDTO;
import com.company.autoservice.dtos.response.AddressResponseDTO;
import com.company.autoservice.service.address.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @Operation(
            description = "POST endpoint to create address",
            summary = "create"
    )
    @PostMapping
    public ResponseEntity<AddressResponseDTO> create(
            @Valid @RequestBody AddressRequestDTO addressRequestDTO
    ) {
        AddressResponseDTO addressResponseDTO = addressService.create(addressRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressResponseDTO);
    }


    @Operation(
            description = "GET endpoint to get address by ID",
            summary = "get by ID"
    )
    @GetMapping("/{addressID}")
    public ResponseEntity<AddressResponseDTO> getByID(
            @PathVariable Long addressID
    ) {
        AddressResponseDTO address = addressService.getByID(addressID);
        return ResponseEntity.ok(address);
    }


    @Operation(
            description = "PUT endpoint to update address",
            summary = "update"
    )
    @PutMapping("/{addressID}")
    public ResponseEntity<AddressResponseDTO> update(
            @PathVariable Long addressID,
            @Valid @RequestBody AddressRequestDTO addressRequestDTO
    ) {
        AddressResponseDTO updatedAddress = addressService.update(addressID, addressRequestDTO);
        return ResponseEntity.ok(updatedAddress);
    }


    @Operation(
            description = "DELETE endpoint to delete address by ID",
            summary = "delete"
    )
    @DeleteMapping("/{addressID}")
    public ResponseEntity<String> delete(
            @PathVariable Long addressID
    ) {
        addressService.delete(addressID);
        return ResponseEntity.ok("Successfully deleted!");
    }
}