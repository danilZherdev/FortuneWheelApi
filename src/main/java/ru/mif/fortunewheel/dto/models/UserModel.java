package ru.mif.fortunewheel.dto.models;

import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.Model;
import ru.mif.fortunewheel.enums.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class UserModel implements Model<User> {

    @Email
    private String email;
    @Min(value = 8, message = "Пароль должен быть длиннее 8 символов")
    @Max(value = 512, message = "Пароль не может превышать 512 символов")
    private String hash;

    public UserModel() {}

    public UserModel(String email, String hash) {
        this.email = email;
        this.hash = hash;
    }

    public String getEmail() {
        return email;
    }

    public String getHash() {
        return hash;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public User toEntity() {
        return new User(email, hash, UserRole.CUSTOMER);
    }
}
