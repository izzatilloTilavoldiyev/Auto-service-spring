package com.company.autoservice.service.company;

import com.company.autoservice.dtos.request.CompanyCreateDTO;
import com.company.autoservice.dtos.response.CompanyResponseDTO;
import org.springframework.data.domain.Page;

public interface CompanyService {

    CompanyResponseDTO create(CompanyCreateDTO companyCreateDTO);

    CompanyResponseDTO getByID(Long companyID);

    Page<CompanyResponseDTO> getAllPages(Integer page, Integer size);

    Page<CompanyResponseDTO> searchByName(String name, Integer page, Integer size);

    CompanyResponseDTO update(Long companyID, CompanyResponseDTO companyResponseDTO);

    void delete(Long companyID);
}
