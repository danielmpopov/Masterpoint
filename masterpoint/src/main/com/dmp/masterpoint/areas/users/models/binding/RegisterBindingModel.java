package com.dmp.masterpoint.areas.users.models.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegisterBindingModel {
    private static final String USERNAME_ERROR_MESSAGE = "Моля въведете потребителско име с дължина от 3 до 15 символа!";
    private static final String PASSWORD_ERROR_MESSAGE = "Моля въведете парола с дължина от 5 до 20 символа!";
    private static final String CONFIRM_PASSWORD_ERROR_MESSAGE = "Моля потвърдете паролата!";
    private static final String EMAIL_ERROR_MESSAGE = "Моля въведете реален email!";
    private static final String TYPE_ERROR_MESSAGE = "Моля изберете майстор или клиент!";

    @NotEmpty(message = USERNAME_ERROR_MESSAGE)
    @Size(min = 3, max = 15, message = USERNAME_ERROR_MESSAGE)
    private String username;

    @NotEmpty(message = PASSWORD_ERROR_MESSAGE)
    @Size(min = 5, max = 20, message = PASSWORD_ERROR_MESSAGE)
    private String password;

    @NotEmpty(message = CONFIRM_PASSWORD_ERROR_MESSAGE)
    private String confirmPassword;

    @NotEmpty(message = EMAIL_ERROR_MESSAGE)
    @Email(message = EMAIL_ERROR_MESSAGE)
    private String email;

    @NotEmpty(message = TYPE_ERROR_MESSAGE)
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
