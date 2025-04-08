package com.example.ems.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDTO {
    private Long documentId;
    private String documentName;
    private String filePath;
    private String documentType;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private boolean isVerified;
}
