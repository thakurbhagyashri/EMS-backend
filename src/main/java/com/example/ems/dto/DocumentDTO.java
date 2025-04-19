package com.example.ems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("isVerified")
    private Boolean isVerified;

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }
}
