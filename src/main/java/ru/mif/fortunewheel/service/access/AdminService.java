package ru.mif.fortunewheel.service.access;

import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.service.AccountService;

@Service
public class AdminService implements AccountService<User> {

    @Override
    public TokenData<User> authenticate(String username, String password) {
        return null;
    }
}
