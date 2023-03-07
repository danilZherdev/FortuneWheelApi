package ru.mif.fortunewheel.service.access;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.Page;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.dto.data.UserData;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.repository.UserRepository;
import ru.mif.fortunewheel.security.AuthenticatedUser;
import ru.mif.fortunewheel.security.AuthenticationException;
import ru.mif.fortunewheel.security.ForbiddenException;
import ru.mif.fortunewheel.security.mapper.UserJWTMapper;
import ru.mif.fortunewheel.service.ReadOnlyService;
import ru.mif.fortunewheel.service.ServiceException;
import ru.mif.fortunewheel.service.UserService;

import java.util.stream.Collectors;

@Service
public class CustomerService implements UserService<User>, ReadOnlyService<User> {

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final UserJWTMapper mapper;
    private final UserRepository repository;
    private final AuthenticatedUser authenticatedUser;

    public CustomerService(UserJWTMapper mapper, UserRepository repository, AuthenticatedUser authenticatedUser) {
        this.mapper = mapper;
        this.repository = repository;
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public TokenData authenticate(String username, String password) {
        var user = repository.findByEmailAndHashAndRole(username, password, UserRole.CUSTOMER)
                .orElseThrow(() -> new ServiceException("User with username = %s and hash = %s not found.", username, password));
        return mapper.create(user);
    }

    @Override
    public Data<User> read(long id) {
        return repository.findByIdAndRole(id, UserRole.CUSTOMER).map(UserData::new)
                .orElseThrow(() -> new ServiceException(logger, "User with id = %s not found", id));
    }

    @Override
    public Data<User> readForUserOnly(long id) {
        //get current user from SecurityContextHolder
        var currentUser = authenticatedUser.get();
        if (currentUser.isEmpty()) {
            throw new AuthenticationException("Authentication failed.");
        }
        //get resource by id
        var user = repository.findByIdAndRole(id, UserRole.CUSTOMER)
                .orElseThrow(() -> new ServiceException(logger, "User with id = %s not found.", id));
        //check if resource is resource of authenticated user
        if (user.getId() != currentUser.get().getId()) {
            throw new ForbiddenException("This resource is not your.");
        }
        // give resource
        return new UserData(user);
    }

    @Override
    public Page<User> read(int number, int size) {
        var page = repository.findAllByRole(UserRole.CUSTOMER, PageRequest.of(number, size));
        var items = page.getContent()
                .stream()
                .map(UserData::new)
                .collect(Collectors.toList());
        return new Page(page, items);
    }
}