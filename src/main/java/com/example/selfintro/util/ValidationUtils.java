package com.example.selfintro.util;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import java.util.stream.Collectors;

public class ValidationUtils {

    /**
     * Check for validation errors, concatenate them, and add to the model.
     * 
     * @param result The BindingResult from the controller method.
     * @param model  The Model to add the error message to.
     * @return true if there are errors, false otherwise.
     */
    public static boolean hasErrorsAndPopulate(BindingResult result, Model model) {
        if (result.hasErrors()) {
            String errorMessage = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining("\n"));
            model.addAttribute("error", errorMessage);
            return true;
        }
        return false;
    }
}
