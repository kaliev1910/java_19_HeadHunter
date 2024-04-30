package com.example.java_19_headhunter.exeptions.handler;


import com.example.java_19_headhunter.exeptions.ErrorResponseBody;
import com.example.java_19_headhunter.exeptions.ResumeNotFoundException;
import com.example.java_19_headhunter.exeptions.SortedCriteriaException;
import com.example.java_19_headhunter.service.interfaces.ErrorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
//
//    private final ErrorService errorService;
//
//    @ExceptionHandler(ResumeNotFoundException.class)
//    public ResponseEntity<ErrorResponseBody> noSuchElement(ResumeNotFoundException exception) {
//        return new ResponseEntity<>(errorService.makeResponse(exception), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(SortedCriteriaException.class)
//    public ResponseEntity<ErrorResponseBody> noSuchElement(SortedCriteriaException exception) {
//        return new ResponseEntity<>(errorService.makeResponse(exception), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException exception) {
//        return new ResponseEntity<>(errorService.makeResponse(exception.getBindingResult()), HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(NoSuchElementException.class)
    public String  notFoundHandler(Model model, HttpServletRequest request){
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";

    }
}
