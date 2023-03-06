package ru.mif.fortunewheel.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.dto.data.UserData;
import ru.mif.fortunewheel.dto.models.UserModel;
import ru.mif.fortunewheel.service.access.CustomerService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomerService customerService;

    public UserController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/auth")
    public TokenData<User> auth(UserModel user) {
        return customerService.authenticate(user.getEmail(), user.getHash());
    }

//    @Secured({UserRole.ADMIN_ROLE, UserRole.CUSTOMER_ROLE})
    @GetMapping("/{id}")
    public UserData get(@PathVariable long id) {
        return null;
    }

//    @Secured(UserRole.ADMIN_ROLE)
    @GetMapping("/{size}/{number}")
    public Page<UserData> get(@PathVariable int size, @PathVariable int number){
        return null;
    }
}
