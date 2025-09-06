package com.lgsoftworks.infrastructure.adapter.out.persistence.specifications;

import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<UserEntity> hasRole(Role role) {
        return (root, query, cb) ->
                role == null ? null : cb.equal(root.get("role"), role);
    }

    public static Specification<UserEntity> hasName(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(cb.lower(root.get("fullName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<UserEntity> hasCity(String city) {
        return (root, query, cb) ->
                city == null ? null : cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%");
    }

}
