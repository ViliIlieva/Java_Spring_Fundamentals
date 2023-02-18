package com.example.exam.models.dtos.bindingModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {

    @NotBlank
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!" )
    private String password;
}
