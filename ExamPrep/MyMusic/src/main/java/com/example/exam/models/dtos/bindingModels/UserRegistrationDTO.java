package com.example.exam.models.dtos.bindingModels;

import com.example.exam.validation.UniqueUserEmail;
import com.example.exam.validation.UniqueUserName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationDTO {

    @NotNull
    @Size(min = 3, max = 20)
    @UniqueUserName(message = "Username should be unique")
    private String username;


    @NotNull
    @Size(min = 3, max = 20)
    private String fullName;

    @Email
    @NotBlank
    @UniqueUserEmail(message = "Email should be unique")
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String password;

    @NotNull
    @Size(min = 3)
    private String confirmPassword;
}
