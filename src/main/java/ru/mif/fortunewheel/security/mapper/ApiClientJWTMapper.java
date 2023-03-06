package ru.mif.fortunewheel.security.mapper;

import ru.mif.fortunewheel.domain.APIClient;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.security.JWTMapper;

import java.security.Key;

public class ApiClientJWTMapper implements JWTMapper<APIClient> {

    private final Key key;

    public ApiClientJWTMapper() {
        this.key = new ;
    }

    @Override
    public TokenData<APIClient> create(APIClient apiClient) {
        return null;
    }

    @Override
    public boolean isValid(String token) {
        return false;
    }

    @Override
    public Data<APIClient> parse(String token) {
        return null;
    }
}
