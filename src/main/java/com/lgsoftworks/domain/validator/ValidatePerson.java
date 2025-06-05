package com.lgsoftworks.domain.validator;

import com.lgsoftworks.domain.exception.PersonWithDocumentExistsException;
import com.lgsoftworks.domain.exception.PersonWithEmailExistsException;
import com.lgsoftworks.domain.port.out.AdminRepositoryPort;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;

public class ValidatePerson {

    private final AdminRepositoryPort adminRepositoryPort;
    private final PlayerRepositoryPort playerRepositoryPort;

    public ValidatePerson(AdminRepositoryPort adminRepositoryPort, PlayerRepositoryPort playerRepositoryPort) {
        this.adminRepositoryPort = adminRepositoryPort;
        this.playerRepositoryPort = playerRepositoryPort;
    }

    public void validate(String documentNumber, String email) {
        validateDocumentNumber(documentNumber);
        validateEmail(email);
    }

    private void validateDocumentNumber(String documentNumber) {
        boolean exists = adminRepositoryPort.findByDocumentNumber(documentNumber).isPresent()
                || playerRepositoryPort.findByDocumentNumber(documentNumber).isPresent();
        if (exists) {
            throw new PersonWithDocumentExistsException(documentNumber);
        }
    }

    private void validateEmail(String email) {
        boolean exists = adminRepositoryPort.findByEmail(email).isPresent()
                || playerRepositoryPort.findByEmail(email).isPresent();
        if (exists) {
            throw new PersonWithEmailExistsException(email);
        }
    }

}
