package ru.mif.fortunewheel.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.Page;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.dto.data.UserData;
import ru.mif.fortunewheel.dto.models.UserModel;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.service.access.AdminService;
import ru.mif.fortunewheel.service.access.CustomerService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomerService service;
    private final AdminService adminService;

    public UserController(CustomerService customerService, AdminService adminService) {
        this.service = customerService;
        this.adminService = adminService;
    }

    @PostMapping("/admin/auth")
    public TokenData<?> adminAuth(@RequestBody UserModel user) {
        return adminService.authenticate(user.getEmail(), user.getHash());
    }

    @PostMapping("/customer/auth")
    public TokenData<?> customerAuth(@RequestBody UserModel user) {
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

    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @DeleteMapping("/{id}")
    public UserData remove(@PathVariable long id) {
        return (UserData) service.remove(id);
    }
}
