package com.example.java_19_headhunter.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    private int id;
    private int parentId;
    private String name;

}
