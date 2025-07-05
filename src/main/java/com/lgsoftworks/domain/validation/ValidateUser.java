package com.lgsoftworks.domain.validation;

import com.lgsoftworks.domain.exception.UserWithCellphoneExistsException;
import com.lgsoftworks.domain.exception.UserWithDocumentExistsException;
import com.lgsoftworks.domain.exception.UserWithEmailExistsException;
import com.lgsoftworks.domain.port.out.FieldAdminRepositoryPort;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;

public class ValidateUser {

    private final FieldAdminRepositoryPort fieldAdminRepositoryPort;
    private final PlayerRepositoryPort playerRepositoryPort;

    public ValidateUser(FieldAdminRepositoryPort fieldAdminRepositoryPort, PlayerRepositoryPort playerRepositoryPort) {
        this.fieldAdminRepositoryPort = fieldAdminRepositoryPort;
        this.playerRepositoryPort = playerRepositoryPort;
    }

    public void validate(String documentNumber, String email, String cellphone) {
        validateExistsDocumentNumber(documentNumber);
        validateExistsEmail(email);
        validateExistsCellphone(cellphone);
    }

    private void validateExistsDocumentNumber(String documentNumber) {
        boolean exists = fieldAdminRepositoryPort.findByDocumentNumber(documentNumber).isPresent()
                || playerRepositoryPort.findByDocumentNumber(documentNumber).isPresent();
        if (exists) {
            throw new UserWithDocumentExistsException(documentNumber);
        }
    }

    private void validateExistsEmail(String email) {
        boolean exists = fieldAdminRepositoryPort.findByEmail(email).isPresent()
                || playerRepositoryPort.findByEmail(email).isPresent();
        if (exists) {
            throw new UserWithEmailExistsException(email);
        }
    }

    private void validateExistsCellphone(String cellphone) {
        boolean exists = fieldAdminRepositoryPort.findByCellphone(cellphone).isPresent()
                || playerRepositoryPort.findByCellphone(cellphone).isPresent();
        if (exists) {
            throw new UserWithCellphoneExistsException(cellphone);
        }
    }

}
