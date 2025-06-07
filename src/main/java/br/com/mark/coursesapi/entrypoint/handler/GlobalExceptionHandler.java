package br.com.mark.coursesapi.entrypoint.handler;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.CoursesCreationException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.DuplicateContactException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidCredentialsException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidEmailException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidPasswordException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidPhoneNumberException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidYearException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.TeachersCreationException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserCreationException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserNotFoundException;
import br.com.mark.coursesapi.usecases.domain.Messages.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleIdentifierNotFoundException(UserNotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error", UserNotFoundException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0001.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRequestOfEmail(InvalidEmailException ex) {

        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", InvalidEmailException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0002.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRequestOfPassword(InvalidCredentialsException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", InvalidCredentialsException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0003.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvalidYearException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRequestOfYear(InvalidYearException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", InvalidYearException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0004.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> httpMessageNotReadableException() {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", HttpMessageNotReadableException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0004.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<Map<String, Object>> handleUserCreationException(UserCreationException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", UserCreationException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0005.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRequestOfPhoneNumber(InvalidPhoneNumberException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", InvalidPhoneNumberException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0006.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DuplicateContactException.class)
    public ResponseEntity<Map<String, Object>> duplicatedContactException() {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error",  DuplicateContactException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0007.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Map<String, Object>> invalidTokenException() {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", InvalidTokenException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0008.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<Map<String, Object>> unauthorizedException() {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", HttpClientErrorException.Unauthorized.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A00011.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> accessDeniedException() {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
        errorResponse.put("error", AccessDeniedException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0009.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CoursesCreationException.class)
    public ResponseEntity<Map<String, Object>> coursesCreationException() {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", CoursesCreationException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0005.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(TeachersCreationException.class)
    public ResponseEntity<Map<String, Object>> teachersCreationException() {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", TeachersCreationException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A0005.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, Object>> invalidPasswordException() {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", InvalidPasswordException.class.getSimpleName());
        errorResponse.put("message", ErrorCodeEnum.A00010.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }





}