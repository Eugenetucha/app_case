package com.test_case.app.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name="users")
public class User implements GrantedAuthority  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Column
    @NotEmpty(message = "Username must not be empty")
    private String username;
    @NotEmpty(message = "Password must not be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    private String password;
    @Column
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String role;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "isNonLocket")
    private boolean isAccountNonLocked;
    @Override
    public String getAuthority() {
        return role;
    }
}