package com.example.mobilelele2.domain.dtoS.banding;


import com.example.mobilelele2.domain.enums.Role;

public class UserRegisterFormDto {

    private String username; // –  username of the user.

    private String password; //– password of the user.

    private String confirmPassword;

    private String firstName; //–  first name of the user.

    private String lastName; //–  last name of the user.

    private Role role; //–  last name of the user.

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
