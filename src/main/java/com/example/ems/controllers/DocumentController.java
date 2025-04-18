package com.example.ems.controller;

import com.example.ems.DTO.DocumentDTO;
import com.example.ems.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees/{employeeId}/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getAllDocuments(@PathVariable Long employeeId) {
        return ResponseEntity.ok(documentService.getAllDocumentsByEmployeeId(employeeId));
    }

    @PostMapping
    public ResponseEntity<DocumentDTO> uploadDocument(@PathVariable Long employeeId, @RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.ok(documentService.uploadDocument(employeeId, documentDTO));
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentDTO> getDocument(@PathVariable Long employeeId, @PathVariable Long documentId) {
        return ResponseEntity.ok(documentService.getDocument(employeeId, documentId));
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long employeeId, @PathVariable Long documentId) {
        documentService.deleteDocument(employeeId, documentId);
        return ResponseEntity.noContent().build();
    }
}
