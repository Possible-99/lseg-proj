package com.lseg_proy.accountsystem.config;

import com.lseg_proy.accountsystem.exception.AccountNotFoundException;
import com.lseg_proy.accountsystem.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * This class handles exceptions globally, providing a centralized
 * point for handling all exceptions thrown within the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles UserNotFoundException.
     *
     * @param ex The exception that was thrown.
     * @param request The HTTP request during which the exception was thrown.
     * @return ResponseEntity with error message and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles AccountNotFoundException.
     *
     * @param ex The exception that was thrown.
     * @param request The HTTP request during which the exception was thrown.
     * @return ResponseEntity with error message and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> handleAccountNotFoundException(AccountNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles MethodArgumentNotValidException typically thrown when @Validated fails.
     *
     * @param ex The MethodArgumentNotValidException exception that was thrown.
     * @return ResponseEntity with a map of field errors and HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other exceptions not specifically handled by other methods in this class.
     *
     * @param ex The exception that was thrown.
     * @param request The HTTP request during which the exception was thrown.
     * @return ResponseEntity with error message and HTTP status INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>("Error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
