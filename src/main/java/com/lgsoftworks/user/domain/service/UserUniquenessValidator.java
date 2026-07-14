package com.lgsoftworks.user.domain.service;

import com.lgsoftworks.user.domain.exception.UserWithCellphoneExistsException;
import com.lgsoftworks.user.domain.exception.UserWithEmailExistsException;
import com.lgsoftworks.user.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUniquenessValidator {

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
