package ru.mif.fortunewheel.security.mapper;

import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.security.JWTMapper;

@Service
public class UserJWTMapper implements JWTMapper<User> {



    @Override
    public TokenData<User> create(User user) {

        return null;
    }

    @Override
    public boolean isValid(String token) {
        return false;
    }

    @Override
    public Data<User> parse(String token) {
        return null;
    }
}
