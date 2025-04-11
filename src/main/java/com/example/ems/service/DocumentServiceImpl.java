package com.example.ems.service;

import com.example.ems.DTO.DocumentDTO;
import com.example.ems.entities.Document;
import com.example.ems.entities.Employee;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.mapper.DocumentMapper;
import com.example.ems.repositories.DocumentRepository;
import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<DocumentDTO> getAllDocumentsByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return documentRepository.findByEmployee(employee).stream()
                .map(DocumentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentDTO getDocument(Long employeeId, Long documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));
        if (!document.getEmployee().getEmployeeId().equals(employeeId)) {
            throw new ResourceNotFoundException("Document does not belong to this employee");
        }
        return DocumentMapper.toDTO(document);
    }

    @Override
    public DocumentDTO uploadDocument(Long employeeId, DocumentDTO documentDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        Document document = DocumentMapper.toEntity(documentDTO);
        document.setEmployee(employee);
        return DocumentMapper.toDTO(documentRepository.save(document));
    }

    @Override
    public void deleteDocument(Long employeeId, Long documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));
        if (!document.getEmployee().getEmployeeId().equals(employeeId)) {
            throw new ResourceNotFoundException("Document does not belong to this employee");
        }
        documentRepository.delete(document);
    }
}