package com.example.java_19_headhunter.exeptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class ErrorResponseBody {
    private String title;
    private Map<String, List<String>> reasons;
}
