package kz.komek.auth.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignupRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;
}
