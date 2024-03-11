package com.example.java_19_headhunter.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ContactInfoDto {
    private int id;
    private int resumeId;
    private int typeId;
    private String contactValue;


}