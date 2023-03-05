package ru.mif.fortunewheel.service;

import ru.mif.fortunewheel.dto.data.TokenData;

public interface AccountService {
    TokenData<?> authenticate(String username, String password);
}
