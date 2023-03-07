package ru.mif.fortunewheel.service.access;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.Model;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.repository.UserRepository;
import ru.mif.fortunewheel.security.mapper.UserJWTMapper;
import ru.mif.fortunewheel.service.ReadOnlyService;
import ru.mif.fortunewheel.service.ServiceException;
import ru.mif.fortunewheel.service.UserService;

@Service
public class CustomerService implements UserService<User>, ReadOnlyService<User> {

    private final UserJWTMapper mapper;
    private final UserRepository repository;

    public CustomerService(UserJWTMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public TokenData authenticate(String username, String password) {
        var user = repository.findByEmailAndHash(username, password)
                .orElseThrow(() -> new ServiceException("User with username = %s and hash = %s not found.", username, password));
        return mapper.create(user);
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