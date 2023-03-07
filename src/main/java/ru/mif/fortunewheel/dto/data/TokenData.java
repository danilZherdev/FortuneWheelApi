package ru.mif.fortunewheel.dto.data;

import ru.mif.fortunewheel.domain.PersistentObject;
import ru.mif.fortunewheel.dto.Data;

import java.time.ZonedDateTime;

public final class TokenData<ENTITY extends PersistentObject> extends Data<ENTITY> {

    private final String token;
    private final ZonedDateTime expiredAt;
    private final Data<ENTITY> data;

    public TokenData(ENTITY entity,
                     String token,
                     ZonedDateTime expiredAt,
                     Data<ENTITY> data) {
        super(entity);
        this.token = token;
        this.expiredAt = expiredAt;
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public Data<ENTITY> getData() {
        return data;
    }


}
