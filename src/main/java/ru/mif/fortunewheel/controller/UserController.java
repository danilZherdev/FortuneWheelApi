package ru.mif.fortunewheel.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.Page;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.dto.data.UserData;
import ru.mif.fortunewheel.dto.models.UserModel;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.service.access.CustomerService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomerService service;

    public UserController(CustomerService customerService) {
        this.service = customerService;
    }

    @PostMapping("/auth")
    public TokenData<?> auth(@RequestBody UserModel user) {
        return service.authenticate(user.getEmail(), user.getHash());
    }

    @Secured(UserRole.CUSTOMER_ROLE)
    @GetMapping("/customer/{id}")
    public UserData getCustomer(@PathVariable long id) {
        return (UserData) service.readForUserOnly(id);
    }

    @Secured({ UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE })
    @GetMapping("/{id}")
    public UserData get(@PathVariable long id) {
        return (UserData) service.read(id);
    }

    @Secured(UserRole.ADMIN_ROLE)
    @GetMapping("/all/{number}/{size}")
    public Page<User> get(@PathVariable int number, @PathVariable int size){
        return service.read(number, size);
    }
}
