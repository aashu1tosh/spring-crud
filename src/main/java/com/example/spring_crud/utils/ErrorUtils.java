package com.example.spring_crud.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public class ErrorUtils {

    /**
     * Extracts validation error messages from the BindingResult.
     *
     * @param result The BindingResult containing validation errors.
     * @return A list of error messages.
     */
    public static List<String> getErrorMessages(BindingResult result) {
        return result.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage) // Extract only the error message
                .collect(Collectors.toList());
    }
}
