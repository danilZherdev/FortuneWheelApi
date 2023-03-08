package ru.mif.fortunewheel.dto.models;

import ru.mif.fortunewheel.domain.Spin;
import ru.mif.fortunewheel.dto.Model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Valid
public class SpinModel implements Model<Spin> {

    @Email
    private String email;
    @Min(value = 8, message = "Hash length is to small")
    @Max(value = 512, message = "Hash length is too big")
    private String userHash;
    @Min(value = 1, message = "Можно начислить не меньше 1 спина")
    @Max(value = 100, message = "Нельзя за одну операцию назначить больше 100 спинов.")
    private int count;

    public SpinModel() {}

    public SpinModel(String email, String userHash, int count) {
        this.email = email;
        this.userHash = userHash;
        this.count = count;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserHash() {
        return userHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public Spin toEntity() {
        throw new UnsupportedOperationException("Transform operation is not implemented for SpinModel");
    }
}
