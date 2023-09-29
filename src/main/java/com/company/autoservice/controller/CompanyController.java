package com.company.autoservice.controller;


import com.company.autoservice.dtos.request.CompanyCreateDTO;
import com.company.autoservice.dtos.response.CompanyResponseDTO;
import com.company.autoservice.service.company.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    //todo: add employee

    @Operation(
            description = "POST endpoint to create Company",
            summary = "create"
    )
    @PostMapping
    public ResponseEntity<CompanyResponseDTO> create(
            @Valid @RequestBody CompanyCreateDTO companyCreateDTO
    ) {
        CompanyResponseDTO companyResponseDTO = companyService.create(companyCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyResponseDTO);
    }


    @Operation(
            description = "GET endpoint to get company by ID",
            summary = "get by ID"
    )
    @GetMapping("/{compID}")
    public ResponseEntity<CompanyResponseDTO> getByID(
            @PathVariable Long compID
    ) {
        CompanyResponseDTO companyResponseDTO = companyService.getByID(compID);
        return ResponseEntity.ok(companyResponseDTO);
    }


    @Operation(
            description = "GET endpoint to get all company pages",
            summary = "get all pages"
    )
    @GetMapping("/all-pages")
    public ResponseEntity<Page<CompanyResponseDTO>> getAllPages(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "12") Integer size
    ) {
        Page<CompanyResponseDTO> allCompanyPages = companyService.getAllPages(page, size);
        return ResponseEntity.ok(allCompanyPages);
    }


    @Operation(
            description = "GET endpoint to search company by giving name",
            summary = "search by name"
    )
    @GetMapping("/search")
    public ResponseEntity<Page<CompanyResponseDTO>> searchByName(
            @RequestParam String name,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        Page<CompanyResponseDTO> allCompanyPages = companyService.searchByName(name, page, size);
        return ResponseEntity.ok(allCompanyPages);
    }


    @Operation(
            description = "PUT endpoint to update company",
            summary = "update"
    )
    @PutMapping("/{compID}")
    public ResponseEntity<CompanyResponseDTO> update(
            @PathVariable Long compID,
            @Valid @RequestBody CompanyResponseDTO companyResponseDTO
    ) {
        CompanyResponseDTO updatedCompany = companyService.update(compID, companyResponseDTO);
        return ResponseEntity.ok(updatedCompany);
    }


    @Operation(
            description = "DELETE endpoint to delete company by ID",
            summary = "delete"
    )
    @DeleteMapping("/{compID}")
    public ResponseEntity<String> delete(
            @PathVariable Long compID
    ) {
        companyService.delete(compID);
        return ResponseEntity.ok("Successfully deleted!");
    }

}