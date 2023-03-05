package ru.mif.fortunewheel.dto.data;

import ru.mif.fortunewheel.domain.PersistentObject;
import ru.mif.fortunewheel.dto.Data;

public final class TokenData<ENTITY extends PersistentObject> extends Data<ENTITY> {

    private final String token;
    private final Data<ENTITY> data;

    public TokenData(ENTITY data, String token, Class<Data<ENTITY>> clazz) {
        super(data);
        this.token = token;
        this.data = create(clazz);
    }

    public String getToken() {
        return token;
    }

    public Data<ENTITY> getData() {
        return data;
    }
}
