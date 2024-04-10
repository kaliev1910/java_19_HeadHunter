
package com.example.java_19_headhunter.controller.mvc;

import com.example.java_19_headhunter.dto.basicDtos.*;
import com.example.java_19_headhunter.dto.createDto.VacancyCreateDto;
import com.example.java_19_headhunter.dto.updateDto.VacancyUpdateDto;
import com.example.java_19_headhunter.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('EMPLOYER')")
public class EmployerController {
    private final UserService userService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final ContactInfoService contactInfoService;
    private final CategoryService categoryService;

    @GetMapping("/vacancies")
    public String getVacancies(Model model, Authentication authentication) {
        List<VacancyDto> vacancies = vacancyService.findByUserEmail(authentication.getName());
        model.addAttribute("vacancies", vacancies);
        return "vacancies/vacancies";
    }

    @GetMapping("/vacancies/create")
    public String showCreateVacancyForm(Model model) {
        model.addAttribute("vacancy", new VacancyCreateDto());
        return "vacancies/create_vacancy";
    }

    @PostMapping("/vacancies")
    public String createVacancy(VacancyCreateDto vacancyDto,
                                Authentication authentication,
                                Model model) {
        UserDto employer = userService.findByEmail(authentication.getName()).get();
        int vacancyId = vacancyService.create(vacancyDto, authentication);
        model.addAttribute("message", "Vacancy created successfully");
        return "redirect:/vacancies";
    }


    @GetMapping("/resumes/{id}")
    public String getResumeDetails(@PathVariable int id, Model model) {
        ResumeDto resume = resumeService.findById(id);
        List<ContactInfoDto> contacts = contactInfoService.findByResumeId(id);
        model.addAttribute("resume", resume);
        model.addAttribute("contacts", contacts);
        return "resumes/resume_info";
    }

    // Метод для отображения формы редактирования вакансии
    @GetMapping("/vacancy/{id}")
    public String showVacancyInfo(@PathVariable("id") int id, Model model) {
        VacancyDto vacancy = (VacancyDto) vacancyService.findById(id);
        model.addAttribute("vacancy", vacancy);
        return "vacancies/vacancy_info"; // Название представления для отображения формы
    }

    @GetMapping("/vacancy/{id}/edit")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        VacancyDto vacancyDto = (VacancyDto) vacancyService.findById(id);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("vacancy", vacancyDto);
        return "vacancies/edit_vacancy"; // Название представления для отображения формы
    }

    // Метод для обновления вакансии
    @PostMapping("/vacancy/{id}/edit")
    public String updateVacancy(@PathVariable("id") int id, VacancyUpdateDto vacancyDto, Authentication authentication,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "vacancies/edit_vacancy"; // Возвращаем форму с ошибками валидации
        }
        vacancyService.update(vacancyDto, authentication);
        return "redirect:/vacancy/{id}";
    }




}