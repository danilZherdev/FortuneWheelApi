package ru.mif.fortunewheel.service.access;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.repository.UserRepository;
import ru.mif.fortunewheel.security.mapper.UserJWTMapper;
import ru.mif.fortunewheel.service.AccountService;
import ru.mif.fortunewheel.service.ServiceException;

@Service
public class AdminService implements AccountService<User> {
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final UserJWTMapper mapper;
    private final UserRepository repository;

    public AdminService(UserJWTMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public TokenData<User> authenticate(String username, String password) {
        var user = repository.findByEmailAndHashAndRole(username, password, UserRole.ADMIN)
                .orElseThrow(() -> new ServiceException("User with username = %s and hash = %s not found.", username, password));
        return mapper.create(user);
    }
}
