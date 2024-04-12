package com.example.java_19_headhunter.exeptions.handler;


import com.example.java_19_headhunter.exeptions.ErrorResponseBody;
import com.example.java_19_headhunter.exeptions.ResumeNotFoundException;
import com.example.java_19_headhunter.exeptions.SortedCriteriaException;
import com.example.java_19_headhunter.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorService errorService;

    @ExceptionHandler(ResumeNotFoundException.class)
    public ResponseEntity<ErrorResponseBody> noSuchElement(ResumeNotFoundException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SortedCriteriaException.class)
    public ResponseEntity<ErrorResponseBody> noSuchElement(SortedCriteriaException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception.getBindingResult()), HttpStatus.BAD_REQUEST);
    }
}
