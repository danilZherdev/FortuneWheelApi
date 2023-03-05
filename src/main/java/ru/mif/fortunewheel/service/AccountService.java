package ru.mif.fortunewheel.service;

import ru.mif.fortunewheel.domain.PersistentObject;
import ru.mif.fortunewheel.dto.data.TokenData;

public interface AccountService<ENTITY extends PersistentObject> {
    TokenData<ENTITY> authenticate(String username, String password);
}
