package com.company.autoservice.repository;

import com.company.autoservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);

    Page<User> findAllByCompanyId(Long company_id, Pageable pageable);

    @Query(value = "update from users u set u.status = 'BLOCKED' where u.id = :ID")
    void blockUser(Long ID);

    @Query(value = "update from users u set u.status = 'ACTIVE' where u.id = :ID")
    void unblockUser(Long ID);
}