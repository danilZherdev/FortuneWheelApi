package ru.mif.fortunewheel.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mif.fortunewheel.repository.UserRepository;
import ru.mif.fortunewheel.security.AuthenticatedUser;
import ru.mif.fortunewheel.security.mapper.UserJWTMapper;

@RestController
@RequestMapping("/test")
public class TestController {
    private final UserRepository repository;
    private final UserJWTMapper userJWTMapper;

    private final AuthenticatedUser authenticatedUser;

    public TestController(UserRepository userRepository,
                          UserJWTMapper userJWTMapper,
                          AuthenticatedUser authenticatedUser) {
        this.repository = userRepository;
        this.userJWTMapper = userJWTMapper;
        this.authenticatedUser = authenticatedUser;
    }

    @Secured("ROLE_CUSTOMER")
    @GetMapping
    public void test() {
        var s = authenticatedUser.get();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/2")
    public void test2() {
        var s = authenticatedUser.get();
    }
}
