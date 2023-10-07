package com.company.autoservice.service.image;

import com.company.autoservice.entity.image.Document;
import com.company.autoservice.exception.ItemNotFoundException;
import com.company.autoservice.repository.image.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final MediaDocumentService mediaDocumentService;

    public Document saveDocument(MultipartFile file) {
        return documentRepository.save(
                Document.builder()
                        .originalName(file.getOriginalFilename())
                        .generatedName(randomUUID() + file.getOriginalFilename())
                        .path(mediaDocumentService.upload(file))
                        .build()
        );
    }

    public Document getDocument(Long id) {
        return documentRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Document not found with ID: " + id)
        );
    }

    @Async
    public void deleteDocument(Long id) {
        documentRepository.deleteDocument(id);
    }
}