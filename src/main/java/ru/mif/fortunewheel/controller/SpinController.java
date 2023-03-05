package ru.mif.fortunewheel.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.dto.data.SpinData;
import ru.mif.fortunewheel.service.access.SpinService;

import java.util.List;

@RestController
@RequestMapping("/spin")
public class SpinController {

    private final SpinService service;

    public SpinController(SpinService service) {
        this.service = service;
    }

    //    @Secured({UserRole.API_CLIENT_ROLE})
    @PostMapping
    List<SpinData> create(String email, String userHash, int count) {
        return service.create(email, userHash, count);
    }

//    @Secured({UserRole.CUSTOMER_ROLE})
    @GetMapping("/count")
    int notUsedCount() {
        return service.notUsedCount(1);
    }

//    @Secured({UserRole.CUSTOMER_ROLE, UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @GetMapping("/all/{userHash}/{number}/{size}")
    Page<SpinData> getAll(@PathVariable String userHash, @PathVariable int number, @PathVariable int size) {
        return service.read(userHash, number, size);
    }

//    @Secured({UserRole.API_CLIENT_ROLE})
    @PutMapping("/burn")
    List<SpinData> burn(String email, String userHash, int count) {
        return service.burn(email, userHash, count);
    }

//    @Secured({UserRole.CUSTOMER_ROLE})
    @GetMapping("/one")
    SpinData one() {
        return service.readNotUsed(1);
    }

}
