package com.lgsoftworks.infrastructure.rest.advice;

import com.lgsoftworks.domain.enums.DocumentType;
import com.lgsoftworks.domain.enums.Status;
import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.exception.*;
import com.lgsoftworks.infrastructure.rest.dto.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
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
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("Error de validación desconocido");

        return ResponseEntity.badRequest().body(new ErrorResponse(message));
    }

    @ExceptionHandler(PasswordNotNullException.class)
    public ResponseEntity<ErrorResponse> handlePasswordNotNullException(PasswordNotNullException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PersonByDocumentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePersonByDocumentNotFoundException(PersonByDocumentNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PersonByEmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePersonByEmailNotFoundException(PersonByEmailNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse("Recurso no encontrado"));
    }

    @ExceptionHandler(CannotDeleteTeamOwnerException.class)
    public ResponseEntity<ErrorResponse> handleCannotDeleteTeamOwnerException(CannotDeleteTeamOwnerException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNotFoundException(PlayerNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(TeamFullException.class)
    public ResponseEntity<ErrorResponse> handleTeamFullException(TeamFullException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PlayerAlreadyInTeamException.class)
    public ResponseEntity<ErrorResponse> handlePlayerAlreadyInTeamException(PlayerAlreadyInTeamException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ReservationByIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleReservationByIdNotFoundException(ReservationByIdNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(FieldNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleFieldNotAvailable(FieldNotAvailableException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(TeamAlreadyHasReservationException.class)
    public ResponseEntity<ErrorResponse> handleTeamAlreadyHasReservationException(TeamAlreadyHasReservationException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PersonByIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePersonByIdNotFoundException(PersonByIdNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PersonWithDocumentExistsException.class)
    public ResponseEntity<ErrorResponse> handlePersonWithDocumentExistsException(PersonWithDocumentExistsException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PersonWithEmailExistsException.class)
    public ResponseEntity<ErrorResponse> handlePersonWithEmailExistsException(PersonWithEmailExistsException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(TeamByIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTeamByIdNotFoundException(TeamByIdNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(DuplicateOwnerException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateOwnerException(DuplicateOwnerException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PersonAlreadyAssignedAsAdminException.class)
    public ResponseEntity<ErrorResponse> handlePersonAlreadyAssignedAsAdminException(PersonAlreadyAssignedAsAdminException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PersonTypeNotAllowedToCreateFieldException.class)
    public ResponseEntity<ErrorResponse> handlePersonTypeNotAllowedToCreateFieldException(PersonTypeNotAllowedToCreateFieldException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PersonTypeNotAllowedToCreateTeamException.class)
    public ResponseEntity<ErrorResponse> handlePersonTypeNotAllowedToCreateTeamException(PersonTypeNotAllowedToCreateTeamException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PersonAlreadyMemberOfTeamException.class)
    public ResponseEntity<ErrorResponse> handlePersonAlreadyMemberOfTeamException(PersonAlreadyMemberOfTeamException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(FieldByIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFieldByIdNotFoundException(FieldByIdNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PlayerAlreadyHasPendingRequestException.class)
    public ResponseEntity<ErrorResponse> handlePlayerAlreadyHasPendingRequestException(PlayerAlreadyHasPendingRequestException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(RequestPlayerByIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRequestPlayerByIdNotFoundException(RequestPlayerByIdNotFoundException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ReservationTimeOutOfRangeException.class)
    public ResponseEntity<ErrorResponse> handleReservationTimeOutOfRangeException(ReservationTimeOutOfRangeException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations()
                .stream()
                .findFirst()
                .map(ConstraintViolation::getMessage)
                .orElse("Validación no válida");

        return ResponseEntity.badRequest().body(new ErrorResponse(message));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handlerHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        String responseMessage = "Solicitud malformada";
        Throwable cause = ex.getMostSpecificCause();

        if (cause.getMessage() != null && cause.getMessage().contains("DocumentType")) {
            String values = Arrays.stream(DocumentType.values())
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));

            responseMessage = "Tipo de documento inválido. Valores válidos: " + values + ".";
        }

        if (cause.getMessage() != null && cause.getMessage().contains("Status")) {
            String values = Arrays.stream(Status.values())
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));

            responseMessage = "Estado de campo inválido. Valores válidos: " + values + ".";
        }

        if (cause.getMessage() != null && cause.getMessage().contains("StatusRequest")) {
            String values = Arrays.stream(StatusRequest.values())
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));

            responseMessage = "Respuesta de solicitud inválida. Valores válidos: " + values + ".";
        }

        return ResponseEntity.badRequest().body(new ErrorResponse(responseMessage));
    }

}
