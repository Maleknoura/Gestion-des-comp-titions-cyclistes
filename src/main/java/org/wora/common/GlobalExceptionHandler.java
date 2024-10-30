package org.wora.common;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        ValidationErrorResponse response = new ValidationErrorResponse();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            response.addError(error.getField(), error.getDefaultMessage());
        }

        return response;
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.addError("endDate", ex.getMessage());
        return response;
    }

    public static class ValidationErrorResponse {
        private List<ValidationError> errors = new ArrayList<>();

        public void addError(String field, String message) {
            errors.add(new ValidationError(field, message));
        }

        public List<ValidationError> getErrors() {
            return errors;
        }

        public void setErrors(List<ValidationError> errors) {
            this.errors = errors;
        }
    }

    public static class ValidationError {
        private String field;
        private String message;

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
