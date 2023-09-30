package com.company.autoservice.service.company;

import com.company.autoservice.dtos.request.CompanyCreateDTO;
import com.company.autoservice.dtos.response.CompanyResponseDTO;
import com.company.autoservice.entity.Address;
import com.company.autoservice.entity.Company;
import com.company.autoservice.enums.Status;
import com.company.autoservice.exception.DuplicateValueException;
import com.company.autoservice.exception.ItemNotFoundException;
import com.company.autoservice.repository.CompanyRepository;
import com.company.autoservice.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final AddressService addressService;

    @Override
    public CompanyResponseDTO create(CompanyCreateDTO companyCreateDTO) {
        checkCompanyUnique(companyCreateDTO.getEmail(), companyCreateDTO.getContactPhone());
        Address address = addressService.getByID(companyCreateDTO.getAddressID());
        Company mappedCompany = modelMapper.map(companyCreateDTO, Company.class);
        mappedCompany.setAddress(address);
        mappedCompany.setStatus(Status.ACTIVE);
        Company savedCompany = companyRepository.save(mappedCompany);
        return modelMapper.map(savedCompany, CompanyResponseDTO.class);
    }

    @Override
    public Company getByID(Long companyID) {
        return getCompanyByID(companyID);
    }

    @Override
    public Page<CompanyResponseDTO> getAllPages(Integer page, Integer size) {
        return companyRepository.findAll(PageRequest.of(page, size))
                .map(this::toDTO);
    }

    @Override
    public Page<CompanyResponseDTO> searchByName(String name, Integer page, Integer size) {
        return companyRepository.searchByName(name, PageRequest.of(page, size))
                .map(this::toDTO);
    }

    @Override
    public CompanyResponseDTO update(Long companyID, CompanyResponseDTO companyResponseDTO) {
        Company company = getCompanyByID(companyID);
        if (companyResponseDTO.getEmail() != null || companyResponseDTO.getContactPhone() != null)
            checkCompanyUnique(companyResponseDTO.getEmail(), companyResponseDTO.getContactPhone());
        if (companyResponseDTO.getAddressID() != null)
            company.setAddress(addressService.getByID(companyResponseDTO.getAddressID()));
        modelMapper.map(companyResponseDTO, company);
        Company updatedCompany = companyRepository.save(company);
        return modelMapper.map(updatedCompany, CompanyResponseDTO.class);
    }

    private CompanyResponseDTO toDTO(Company company) {
        return modelMapper.map(company, CompanyResponseDTO.class);
    }

    @Override
    public void delete(Long companyID) {
        if (!companyRepository.existsById(companyID))
            throw new ItemNotFoundException("Company not found with ID: " + companyID);
        companyRepository.deleteById(companyID);
    }

    private Company getCompanyByID(Long companyID) {
        return companyRepository.findById(companyID).orElseThrow(
                () -> new ItemNotFoundException("Company not found with ID: " + companyID)
        );
    }

    private void checkCompanyUnique(String email, String contactPhone) {
        if (companyRepository.existsByEmail(email))
            throw new DuplicateValueException("Company exists by Email: " + email);
        else if (companyRepository.existsByContactPhone(contactPhone))
            throw new DuplicateValueException("Company exists by Contact phone: " + contactPhone);
    }
}