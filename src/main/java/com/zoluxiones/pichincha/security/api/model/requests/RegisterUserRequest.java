package com.zoluxiones.pichincha.security.api.model.requests;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterUserRequest {

    @NotEmpty
    @NotNull
    private String username;

    @NotEmpty
    @NotNull
    private String names;

    @NotEmpty
    @NotNull
    private String lastNames;

    @NotEmpty
    @NotNull
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty
    @NotNull
    @Length(min = 6, message = "Password should be at least 6 characters")
    private String password;
}
