package ru.mif.fortunewheel.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.domain.SpinPrize;
import ru.mif.fortunewheel.dto.Page;
import ru.mif.fortunewheel.dto.data.SpinPrizeData;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.service.access.SpinPrizeService;

@RestController
@RequestMapping("/spin-prize")
public class SpinPrizeController {

    private final SpinPrizeService service;

    public SpinPrizeController(SpinPrizeService service) {
        this.service = service;
    }

    @Secured({UserRole.CUSTOMER_ROLE})
    @PostMapping("/{spinId}")
    SpinPrizeData create(@PathVariable long spinId) {
        return service.create(spinId);
    }

    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @GetMapping("/all/{number}/{size}")
    Page<SpinPrize> my(@PathVariable int number, @PathVariable int size) {
        return service.read(number, size);
    }

    @Secured({UserRole.CUSTOMER_ROLE, UserRole.API_CLIENT_ROLE})
    @GetMapping("/by-user/{userId}")
    Page<SpinPrize> byUser(long userId, int number, int size) {
        return service.read("my_hash", number, size);
    }

    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @PatchMapping("/give/{id}")
    SpinPrizeData give(long id) {
        return null;
    }
}
