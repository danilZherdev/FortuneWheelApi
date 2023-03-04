package ru.mif.fortunewheel.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.dto.data.PageData;
import ru.mif.fortunewheel.dto.data.UserData;
import ru.mif.fortunewheel.dto.models.UserModel;
import ru.mif.fortunewheel.enums.UserRole;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public UserData register(UserModel model) {
        return null;
    }

    @PatchMapping
    public UserData auth(UserModel user) {
        return null;
    }

    @Secured({UserRole.ADMIN_ROLE, UserRole.CUSTOMER_ROLE})
    @GetMapping("/:id")
    public UserData get(@PathVariable long id) {
        return null;
    }

    @Secured(UserRole.ADMIN_ROLE)
    @GetMapping("{/:size/:number}")
    public PageData<UserData> get(@PathVariable int size, @PathVariable int number){
        return null;
    }

    @DeleteMapping("{/:id}")
    public UserData delete(@PathVariable long id) {
        return null;
    }
}
