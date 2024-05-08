package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.basicDtos.ContactInfoDto;
import com.example.java_19_headhunter.dto.basicDtos.UserDto;
import com.example.java_19_headhunter.dto.basicDtos.VacancyDto;
import com.example.java_19_headhunter.dto.createDto.VacancyCreateDto;
import com.example.java_19_headhunter.dto.updateDto.VacancyUpdateDto;
import com.example.java_19_headhunter.service.interfaces.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class VacancyMvcController {
    private final UserService userService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final ContactInfoService contactInfoService;
    private final CategoryService categoryService;

    @GetMapping("/vacancies")
    public String getVacancies(@RequestParam(name = "filter", required = false, defaultValue = "") String filter,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 5) Pageable pageable,
                               Model model) {
        Page<VacancyDto> vacancies;

        if (filter != null && !filter.isEmpty()) {
            vacancies = vacancyService.getVacanciesWithPagingByCategories(pageable, Integer.parseInt(filter));
        } else {
            vacancies = vacancyService.getVacanciesWithPaging(pageable);
        }
        model.addAttribute("filter", filter);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("url", "/vacancies");
        model.addAttribute("filter", filter);
        return "vacancies/vacancies";
    }

    @GetMapping("/vacancy/create")
    public String showCreateVacancyForm(Model model) {
        model.addAttribute("vacancy", new VacancyCreateDto());
        return "vacancies/create_vacancy";
    }

    @PostMapping("/vacancy/create")
    public String createVacancy(@Valid VacancyCreateDto vacancyDto, Authentication authentication, Model model) {
        UserDto employer = userService.findByEmail(authentication.getName()).get();
        vacancyDto.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        vacancyDto.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        int vacancyId = vacancyService.create(vacancyDto, authentication);
        log.info(authentication.getAuthorities().toString());
        model.addAttribute("message", "Vacancy created successfully");
        return "redirect:/vacancy/" + vacancyId;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vacancy/{id}")
    public String showVacancyInfo(@PathVariable("id") int id, Model model) {
        VacancyDto vacancy = vacancyService.findById(id);
        List<ContactInfoDto> contacts = contactInfoService.findListByResumeId(id);
        model.addAttribute("vacancy", vacancy);

        model.addAttribute("contacts", contacts);
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