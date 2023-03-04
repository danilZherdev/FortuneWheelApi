package ru.mif.fortunewheel.dto.data;

import ru.mif.fortunewheel.domain.Spin;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.enums.SpinStatusType;

public class SpinData implements Data<SpinData, Spin> {

    private SpinStatusType status;
    private UserData user;

    public SpinData() {}

    public SpinData(SpinStatusType status, UserData user) {
        this.status = status;
        this.user = user;
    }

    public SpinStatusType getStatus() {
        return status;
    }

    public UserData getUser() {
        return user;
    }

    @Override
    public SpinData fromEntity(Spin spin) {
        return new SpinData(
                spin.getStatus(),
                new UserData().fromEntity(spin.getUser())
        );
    }
}
