package com.company.autoservice.repository;

import com.company.autoservice.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = """
           select * from company c where lower(c.name) like 
           lower(concat('%', :name, '%') )
           """, nativeQuery = true)
    Page<Company> searchByName(String name, Pageable pageable);

    boolean existsByEmail(String email);

    boolean existsByContactPhone(String contactPhone);
}