package com.example.ems.mapper;

import com.example.ems.DTO.DocumentDTO;
import com.example.ems.entities.Document;

public class DocumentMapper {
    public static DocumentDTO toDTO(Document doc) {
        return DocumentDTO.builder()
                .documentId(doc.getDocumentId())
                .documentName(doc.getDocumentName())
                .filePath(doc.getFilePath())
                .documentType(doc.getDocumentType())
                .issueDate(doc.getIssueDate())
                .expiryDate(doc.getExpiryDate())
                .isVerified(doc.isVerified())
                .build();
    }

    public static Document toEntity(DocumentDTO dto) {
        return Document.builder()
                .documentName(dto.getDocumentName())
                .filePath(dto.getFilePath())
                .documentType(dto.getDocumentType())
                .issueDate(dto.getIssueDate())
                .expiryDate(dto.getExpiryDate())
                .isVerified(dto.isVerified())
                .build();
    }
}