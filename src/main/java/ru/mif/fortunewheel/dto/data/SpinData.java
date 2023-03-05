package ru.mif.fortunewheel.dto.data;

import ru.mif.fortunewheel.domain.Spin;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.enums.SpinStatusType;

public class SpinData extends Data<Spin> {

    private final SpinStatusType status;
    private final UserData user;

    public SpinData(Spin spin) {
        super(spin);
        this.status = spin.getStatus();
        this.user = new UserData(spin.getUser());
    }

    public SpinStatusType getStatus() {
        return status;
    }

    public UserData getUser() {
        return user;
    }
}
