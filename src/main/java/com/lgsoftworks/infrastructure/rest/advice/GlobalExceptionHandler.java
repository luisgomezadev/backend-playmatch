package com.lgsoftworks.infrastructure.rest.advice;

import com.lgsoftworks.domain.enums.*;
import com.lgsoftworks.domain.exception.*;
import com.lgsoftworks.infrastructure.rest.dto.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // --- 400 BAD REQUEST: Excepciones de negocio comunes ---
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
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    // --- 401 UNAUTHORIZED ---
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
    }

    // --- 403 FORBIDDEN ---
    @ExceptionHandler({AccessDeniedException.class, ForbiddenException.class})
    public ResponseEntity<ErrorResponse> handleAccessDenied(Exception ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Acceso denegado: " + ex.getMessage()));
    }

    // --- 404 NOT FOUND ---
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Recurso no encontrado"));
    }

    // --- 400 BAD REQUEST: Errores de validación ---
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("Error de validación desconocido");
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .findFirst()
                .map(ConstraintViolation::getMessage)
                .orElse("Validación no válida");
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), resolveEnumValidationMessage(ex.getMostSpecificCause())));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    // --- Delegación de ResponseStatusException (genérica) ---
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatus(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(new ErrorResponse(ex.getStatusCode().value(), ex.getReason()));
    }

    // --- Utilidad para mostrar enums válidos en errores de parseo ---
    private String resolveEnumValidationMessage(Throwable cause) {
        if (cause == null || cause.getMessage() == null) return "Solicitud malformada";

        if (cause.getMessage().contains("DocumentType")) {
            return buildEnumMessage("Tipo de documento inválido", DocumentType.values());
        }

        if (cause.getMessage().contains("StatusReservation")) {
            return buildEnumMessage("Estado de reserva inválido", StatusReservation.values());
        }

        if (cause.getMessage().contains("StatusRequest")) {
            return buildEnumMessage("Respuesta de solicitud inválida", StatusRequest.values());
        }

        if (cause.getMessage().contains("Status")) {
            return buildEnumMessage("Estado de campo inválido", Status.values());
        }

        if (cause.getMessage().contains("Role")) {
            return buildEnumMessage("Rol inválido", Role.values());
        }

        return "Solicitud malformada";
    }

    private String buildEnumMessage(String prefix, Enum<?>[] values) {
        String validValues = Arrays.stream(values)
                .map(Enum::name)
                .collect(Collectors.joining(", "));
        return prefix + ". Valores válidos: " + validValues + ".";
    }
}
