package com.dmp.masterpoint.users.models.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegisterBindingModel {
    @NotEmpty(message = "Username is required.")
    @Size(min = 3, max = 15)
    private String username;

    @NotEmpty(message = "Password is required.")
    @Size(min = 5, max = 20)
    private String password;

    @NotEmpty(message = "Confirm Password is required")
    private String confirmPassword;

    @NotEmpty(message = "Email is required.")
    @Email
    private String email;

    private String type;

    public RegisterBindingModel() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
