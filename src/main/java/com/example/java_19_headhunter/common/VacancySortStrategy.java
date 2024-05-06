package com.example.java_19_headhunter.common;


import com.example.java_19_headhunter.exeptions.SortedCriteriaException;
import com.example.java_19_headhunter.models.Vacancy;

import java.util.Comparator;
import java.util.List;

public enum VacancySortStrategy {

    BY_ID("id") {
        @Override
        public List<Vacancy> sortingVacancies(List<Vacancy> resumes) {
            resumes.sort(Comparator.comparing(Vacancy::getId));
            return resumes;
        }
    },
    BY_NAME("name") {
        @Override
        public List<Vacancy> sortingVacancies(List<Vacancy> resumes) {
            resumes.sort(Comparator.comparing(Vacancy::getName));
            return resumes;
        }
    },
    BY_UPDATED_TIME("updated_time") {
        @Override
        public List<Vacancy> sortingVacancies(List<Vacancy> resumes) {
            resumes.sort(Comparator.comparing(Vacancy::getUpdatedTime));
            return resumes;
        }
    },

//    BY_CATEGORY_ID("CATEGORY_ID") {
//        @Override
//        public List<Vacancy> sortingVacancies(List<Vacancy> resumes) {
//            resumes.sort(Comparator.comparing(Vacancy::getCategoryId));
//            return resumes;
//        }
//    },


    BY_SALARY("SALARY") {
        @Override
        public List<Vacancy> sortingVacancies(List<Vacancy> resumes) {
            resumes.sort(Comparator.comparing(Vacancy::getSalary));
            return resumes;
        }
    },

    BY_EXP_FROM("EXP_FROM") {
        @Override
        public List<Vacancy> sortingVacancies(List<Vacancy> resumes) {
            resumes.sort(Comparator.comparing(Vacancy::getSalary));
            return resumes;
        }
    },

    BY_EXP_TO("EXP_TO") {
        @Override
        public List<Vacancy> sortingVacancies(List<Vacancy> resumes) {
            resumes.sort(Comparator.comparing(Vacancy::getSalary));
            return resumes;
        }
    };

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

    public abstract List<Vacancy> sortingVacancies(List<Vacancy> vacancies);

}
