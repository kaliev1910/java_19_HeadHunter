package com.example.java_19_headhunter.models;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class Category {

    private int id;
    private int parentId;
    private String name;

}
