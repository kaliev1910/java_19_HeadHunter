package com.example.java_19_headhunter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private int id;
    private int resumeId;
    private int vacancyId;
    private boolean confirmation;
}
