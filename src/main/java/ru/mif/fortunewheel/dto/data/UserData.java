package ru.mif.fortunewheel.dto.data;

import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.Data;

public class UserData implements Data<UserData, User>  {

    private long id;
    private String email;

    public UserData() {}

    public UserData(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public UserData fromEntity(User user) {
        return new UserData(user.getId(), user.getEmail());
    }
}
