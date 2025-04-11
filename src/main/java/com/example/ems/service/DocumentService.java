package com.example.ems.service;

import com.example.ems.DTO.DocumentDTO;

import java.util.List;

public interface DocumentService {
    List<DocumentDTO> getAllDocumentsByEmployeeId(Long employeeId);
    DocumentDTO getDocument(Long employeeId, Long documentId);
    DocumentDTO uploadDocument(Long employeeId, DocumentDTO documentDTO);
    void deleteDocument(Long employeeId, Long documentId);
}
