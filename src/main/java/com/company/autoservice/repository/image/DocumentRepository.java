package com.company.autoservice.repository.image;

import com.company.autoservice.entity.image.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("select d from Document d where d.deleted = false and d.path = ?1")
    Optional<Document> findByPath(String fileName);

//    @Query("select d from Document d where d.deleted = false and d.createdBy = ?1")
//    Page<Document> findAllByCreatedBy(Long id, Pageable pageable);

    @Query("select d from Document d where d.deleted = false and d.generatedName = ?1")
    Optional<Document> findByName(String fileName);

    @Query("select d from Document d where d.deleted = false and d.generatedName = ?1")
    Document findByNameLink(String fileName);

    @Transactional
    @Modifying
    @Query("update Document d set d.deleted = true where d.deleted = false and d.id = ?1")
    void deleteDocument(Long id);
}