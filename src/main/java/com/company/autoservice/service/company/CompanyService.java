package com.company.autoservice.service.company;

import com.company.autoservice.dtos.request.CompanyCreateDTO;
import com.company.autoservice.dtos.request.CompanyUpdateDTO;
import com.company.autoservice.dtos.response.CompanyResponseDTO;
import com.company.autoservice.entity.Company;
import org.springframework.data.domain.Page;

public interface CompanyService {

    CompanyResponseDTO create(CompanyCreateDTO companyCreateDTO);

    Company getByID(Long companyID);

    Page<CompanyResponseDTO> getAllPages(Integer page, Integer size);

    Page<CompanyResponseDTO> searchByName(String name, Integer page, Integer size);

    CompanyResponseDTO update(Long companyID, CompanyUpdateDTO companyUpdateDTO);

    void delete(Long companyID);
}
