package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.basicDtos.UserDto;
import com.example.java_19_headhunter.dto.basicDtos.VacancyDto;
import com.example.java_19_headhunter.dto.createDto.VacancyCreateDto;
import com.example.java_19_headhunter.dto.updateDto.VacancyUpdateDto;
import com.example.java_19_headhunter.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VacancyMvcController {
    private final UserService userService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final ContactInfoService contactInfoService;
    private final CategoryService categoryService;

    @GetMapping("/vacancies")
    public String getVacancies(@RequestParam(name = "page") Integer page, Model model) {
        List<VacancyDto> vacancies = vacancyService.getVacanciesWithPaging(page, 6);
        model.addAttribute("vacancies", vacancies);
        return "vacancies/vacancies";
    }

    @GetMapping("/vacancy/create")
    public String showCreateVacancyForm(Model model) {
        model.addAttribute("vacancy", new VacancyCreateDto());
        return "vacancies/create_vacancy";
    }

    @PostMapping("/vacancy/create")
    public String createVacancy(VacancyCreateDto vacancyDto, Authentication authentication, Model model) {
        UserDto employer = userService.findByEmail(authentication.getName()).get();
        int vacancyId = vacancyService.create(vacancyDto, authentication);
        model.addAttribute("message", "Vacancy created successfully");
        return "redirect:/vacancy/" + vacancyId;
    }


    @GetMapping("/vacancy/{id}")
    public String showVacancyInfo(@PathVariable("id") int id, Model model) {
        VacancyDto vacancy = vacancyService.findById(id);
        model.addAttribute("vacancy", vacancy);
        return "vacancies/vacancy_info"; //
    }

    @GetMapping("/vacancy/{id}/edit")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        VacancyDto vacancyDto = vacancyService.findById(id);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("vacancy", vacancyDto);
        return "vacancies/edit_vacancy";
    }

    @PostMapping("/vacancy/{id}/edit")
    public String updateVacancy(@PathVariable("id") int id, VacancyUpdateDto vacancyDto, Authentication authentication, BindingResult result) {
        if (result.hasErrors()) {
            return "vacancies/edit_vacancy";
        }
        vacancyService.update(vacancyDto, authentication);
        return "redirect:/vacancy/{id}";
    }


}