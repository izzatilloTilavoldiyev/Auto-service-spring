package com.company.autoservice.repository;

import com.company.autoservice.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);

    Page<User> findAllByCompanyId(Long company_id, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update users set status = 'BLOCKED' where id = :userID", nativeQuery = true)
    void blockUser(Long userID);

    @Transactional
    @Modifying
    @Query(value = "update users set status = 'ACTIVE' where id = :userID", nativeQuery = true)
    void unblockUser(Long userID);
}