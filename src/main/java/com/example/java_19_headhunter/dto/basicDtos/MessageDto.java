package com.example.java_19_headhunter.dto.basicDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MessageDto {

    private int id;
    private int respondedApplicantsId;

    @NotBlank(message = "Content is mandatory")
    @Size(max = 2000, message = "Content should not be greater than 2000 characters")
    private String content;

    // no need to validate 'timestamp' as it is system generated
    private Timestamp timestamp;
}