package com.lgsoftworks.infrastructure.adapter.in.rest.advice;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.exception.*;
import com.lgsoftworks.domain.reservation.enums.StatusReservation;
import com.lgsoftworks.domain.user.enums.Role;
import com.lgsoftworks.infrastructure.adapter.in.rest.dto.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // --- 400 BAD REQUEST: Excepciones de negocio ---
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            PasswordNotNullException.class,
            FieldNotAvailableException.class,
            UserAlreadyHasReservationException.class,
            UserWithEmailExistsException.class,
            UserAlreadyAssignedAsAdminException.class,
            UserTypeNotAllowedToCreateFieldException.class,
            ReservationTimeOutOfRangeException.class,
            SQLIntegrityConstraintViolationException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            DataIntegrityViolationException.class,
            UserByCellphoneNotFoundException.class,
            UserWithCellphoneExistsException.class
    })
    public ErrorResponse handleBadRequest(RuntimeException ex) {
        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    // --- 404 NOT FOUND ---
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            UserByEmailNotFoundException.class,
            ReservationByIdNotFoundException.class,
            UserByIdNotFoundException.class,
            FieldByIdNotFoundException.class,
    })
    public ErrorResponse handleNotFoundException(RuntimeException ex) {
        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler()
    public ErrorResponse handleNotFound(NoResourceFoundException ex) {
        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("Recurso no encontrado")
                .timestamp(LocalDateTime.now())
                .build();
    }

    // --- 401 UNAUTHORIZED ---
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            InvalidCredentialsException.class,
            AuthorizationDeniedException.class
    })
    public ErrorResponse handleUnauthorized(RuntimeException ex) {
        return ErrorResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    // --- 403 FORBIDDEN ---
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class, ForbiddenException.class})
    public ErrorResponse handleAccessDenied(Exception ex) {
        return ErrorResponse.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message("Acceso denegado: " + ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    // --- 400 BAD REQUEST: Errores de validación ---
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Parámetros inválidos")
                .details(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolation(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Violación de restricción")
                .details(violations.stream()
                        .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    // --- Utilidad para mostrar enums válidos en errores de parseo ---
    private String resolveEnumValidationMessage(Throwable cause) {
        if (cause == null || cause.getMessage() == null) return "Solicitud malformada";

        if (cause.getMessage().contains("StatusReservation")) {
            return buildEnumMessage("Estado de reserva inválido", com.lgsoftworks.domain.reservation.enums.StatusReservation.values());
        }

        if (cause.getMessage().contains("Status")) {
            return buildEnumMessage("Estado de campo inválido", Status.values());
        }

        if (cause.getMessage().contains("Role")) {
            return buildEnumMessage("Rol inválido", com.lgsoftworks.domain.user.enums.Role.values());
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
