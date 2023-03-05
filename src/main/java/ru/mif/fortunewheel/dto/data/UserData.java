package ru.mif.fortunewheel.dto.data;

import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.Data;

public final class UserData extends Data<User>  {

    private final long id;
    private final String email;

    public UserData(User user) {
        super(user);
        this.id = user.getId();
        this.email = user.getEmail();
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
