package com.lgsoftworks.domain.validation;

import com.lgsoftworks.domain.exception.UserWithCellphoneExistsException;
import com.lgsoftworks.domain.exception.UserWithEmailExistsException;
import com.lgsoftworks.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateUser {

    private final UserRepositoryPort userRepositoryPort;

    public void validate(String email, String cellphone) {
        validateExistsEmail(email);
        validateExistsCellphone(cellphone);
    }

    private void validateExistsEmail(String email) {
        boolean exists = userRepositoryPort.findByEmail(email).isPresent();
        if (exists) {
            throw new UserWithEmailExistsException(email);
        }
    }

    private void validateExistsCellphone(String cellphone) {
        boolean exists = userRepositoryPort.findByCellphone(cellphone).isPresent();
        if (exists) {
            throw new UserWithCellphoneExistsException(cellphone);
        }
    }

}
