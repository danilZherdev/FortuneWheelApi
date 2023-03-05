package ru.mif.fortunewheel.service.access;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.Model;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.service.ReadOnlyService;
import ru.mif.fortunewheel.service.UserService;

@Service
public class CustomerService implements UserService<User>, ReadOnlyService<User> {

    @Override
    public TokenData<?> authenticate(String username, String password) {
        return null;
    }

    @Override
    public TokenData<?> registration(Model<User> model) {
        return null;
    }

    @Override
    public Data<User> read(long id) {
        return null;
    }

    @Override
    public Page<Data<User>> read(int number, int size) {
        return null;
    }
}