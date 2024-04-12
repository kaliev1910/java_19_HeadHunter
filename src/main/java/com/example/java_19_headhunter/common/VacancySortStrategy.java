package com.example.java_19_headhunter.common;


import com.example.java_19_headhunter.exeptions.SortedCriteriaException;
import com.example.java_19_headhunter.models.Resume;

import java.util.Comparator;
import java.util.List;

public enum VacancySortStrategy {

    BY_ID("id") {
        @Override
        public List<Resume> sortingVacancies(List<Resume> resumes) {
            resumes.sort(Comparator.comparing(Resume::getId));
            return resumes;
        }
    },
    BY_NAME("name") {
        @Override
        public List<Resume> sortingVacancies(List<Resume> resumes) {
            resumes.sort(Comparator.comparing(Resume::getName));
            return resumes;
        }
    },
    BY_UPDATE_TIME("update_time") {
        @Override
        public List<Resume> sortingVacancies(List<Resume> resumes) {
            resumes.sort(Comparator.comparing(Resume::getUpdatedTime));
            return resumes;
        }
    },

    BY_CATEGORY_ID("CATEGORY_ID") {
        @Override
        public List<Resume> sortingVacancies(List<Resume> resumes) {
            resumes.sort(Comparator.comparing(Resume::getCategoryId));
            return resumes;
        }
    },



    BY_EXPECTED_SALARY("EXPECTED_SALARY") {
        @Override
        public List<Resume> sortingVacancies(List < Resume > resumes) {
            resumes.sort(Comparator.comparing(Resume::getExpectedSalary));
            return resumes;
        }
    }

    ;

    private final String value;

    VacancySortStrategy(String sortedBy) {
        this.value = sortedBy;
    }

    public static VacancySortStrategy fromString(String sortedBy) {
        for (var e : VacancySortStrategy.values()) {
            if (e.value.equalsIgnoreCase(sortedBy)) {
                return e;
            }
        }
        throw new SortedCriteriaException("Sorted criteria not found");
    }

    public abstract List<Resume> sortingVacancies(List<Resume> moviresumess);

}
