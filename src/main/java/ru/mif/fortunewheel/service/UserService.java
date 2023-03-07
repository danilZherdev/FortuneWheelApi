package ru.mif.fortunewheel.service;

import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.Model;
import ru.mif.fortunewheel.dto.data.TokenData;

public interface UserService<USER extends User> extends AccountService {
}
