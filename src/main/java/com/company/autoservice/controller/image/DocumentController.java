package com.company.autoservice.controller.image;

import com.company.autoservice.entity.image.Document;
import com.company.autoservice.service.image.DocumentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Document> uploadFile(@Valid @NotNull @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(
                documentService.saveDocument(file)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@Valid @NotNull @PathVariable Long id) {
        return ResponseEntity.ok(documentService.getDocument(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@Valid @NotNull @PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok(true);
    }
}