package com.example.java_19_headhunter.dto.basicDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PositionDto {

    private long id;
    private long categoryId;
    private String positionName;
}

