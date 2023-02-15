package com.example.coffee_shop.models.dtos.bindingModels;

import com.example.coffee_shop.util.validation.UniqueUserEmail;
import com.example.coffee_shop.util.validation.UniqueUserName;
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
    @Size(min = 5, max = 20)
    @UniqueUserName(message = "Username should be unique")
    private String username;
    private String firstName;
    @NotNull
    @Size(min = 5, max = 20)
    private String lastName;
    @Email
    @NotBlank
    @UniqueUserEmail(message = "Email should be unique")
    private String email;
    @NotNull
    @Size(min = 3)
    private String password;
    @NotNull
    @Size(min = 3)
    private String confirmPassword;
}
