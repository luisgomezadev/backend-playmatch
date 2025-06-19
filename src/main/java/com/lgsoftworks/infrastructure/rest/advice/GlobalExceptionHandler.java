package com.lgsoftworks.infrastructure.rest.advice;

import com.lgsoftworks.domain.enums.DocumentType;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.enums.Status;
import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.exception.*;
import com.lgsoftworks.infrastructure.rest.dto.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Agrupación de excepciones que retornan 400 con el mensaje de la excepción
    @ExceptionHandler({
            PasswordNotNullException.class,
            UserByDocumentNotFoundException.class,
            UserByEmailNotFoundException.class,
            CannotDeleteTeamOwnerException.class,
            PlayerNotFoundException.class,
            TeamFullException.class,
            PlayerAlreadyInTeamException.class,
            ReservationByIdNotFoundException.class,
            FieldNotAvailableException.class,
            TeamAlreadyHasReservationException.class,
            UserByIdNotFoundException.class,
            UserWithDocumentExistsException.class,
            UserWithEmailExistsException.class,
            TeamByIdNotFoundException.class,
            DuplicateOwnerException.class,
            UserAlreadyAssignedAsAdminException.class,
            UserTypeNotAllowedToCreateFieldException.class,
            UserTypeNotAllowedToCreateTeamException.class,
            PlayerAlreadyMemberOfTeamException.class,
            FieldByIdNotFoundException.class,
            PlayerAlreadyHasPendingRequestException.class,
            RequestPlayerByIdNotFoundException.class,
            ReservationTimeOutOfRangeException.class,
            SQLIntegrityConstraintViolationException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .findFirst().map(FieldError::getDefaultMessage)
                .orElse("Error de validación desconocido");
        return ResponseEntity.badRequest().body(new ErrorResponse(message));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .findFirst().map(ConstraintViolation::getMessage)
                .orElse("Validación no válida");
        return ResponseEntity.badRequest().body(new ErrorResponse(message));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoResourceFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse("Recurso no encontrado"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(resolveEnumValidationMessage(ex.getMostSpecificCause())));
    }

    // Auxiliar para construir mensajes con enums válidos
    private String resolveEnumValidationMessage(Throwable cause) {
        if (cause == null || cause.getMessage() == null) return "Solicitud malformada";

        if (cause.getMessage().contains("DocumentType")) {
            String values = Arrays.stream(DocumentType.values()).map(Enum::name).collect(Collectors.joining(", "));
            return "Tipo de documento inválido. Valores válidos: " + values + ".";
        }

        if (cause.getMessage().contains("Status")) {
            String values = Arrays.stream(Status.values()).map(Enum::name).collect(Collectors.joining(", "));
            return "Estado de campo inválido. Valores válidos: " + values + ".";
        }

        if (cause.getMessage().contains("StatusRequest")) {
            String values = Arrays.stream(StatusRequest.values()).map(Enum::name).collect(Collectors.joining(", "));
            return "Respuesta de solicitud de ingreso a equipo inválida. Valores válidos: " + values + ".";
        }

        if (cause.getMessage().contains("Role")) {
            String values = Arrays.stream(Role.values()).map(Enum::name).collect(Collectors.joining(", "));
            return "Rol inválido. Valores válidos: " + values + ".";
        }

        return "Solicitud malformada";
    }
}
