package com.lgsoftworks.infrastructure.adapter.entity;

import com.lgsoftworks.domain.enums.DocumentType;
import com.lgsoftworks.domain.enums.Permission;
import com.lgsoftworks.domain.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "player")
@NoArgsConstructor
@Getter
@Setter
public class PlayerEntity extends BaseEntity implements UserDetails {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Byte age;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    @Column(unique = true)
    private String documentNumber;
    @Column(unique = true)
    @Email
    private String email;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;
    @Length(min = 5, message = "Contraseña debe tener más de 5 caracteres")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
