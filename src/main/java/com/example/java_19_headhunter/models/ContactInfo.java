package com.example.java_19_headhunter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContactInfo {
    private int id;
    private int resumeId;
    private int typeId;
    private String contactValue;


}