package ru.mif.fortunewheel.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.domain.Spin;
import ru.mif.fortunewheel.dto.Page;
import ru.mif.fortunewheel.dto.data.SpinData;
import ru.mif.fortunewheel.dto.models.SpinModel;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.service.access.SpinService;

import java.util.List;

@RestController
@RequestMapping("/spin")
public class SpinController {

    private final SpinService service;

    public SpinController(SpinService service) {
        this.service = service;
    }

    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @PostMapping
    List<SpinData> create(@RequestBody SpinModel model) {
        return service.create(model.getEmail(), model.getUserHash(), model.getCount());
    }

    @Secured({UserRole.CUSTOMER_ROLE})
    @GetMapping("/count")
    int notUsedCount() {
        return service.notUsedCount(1);
    }

    @Secured({UserRole.CUSTOMER_ROLE, UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @GetMapping("/by-hash/{userHash}/{number}/{size}")
    Page<Spin> getAllByHah(@PathVariable String userHash, @PathVariable int number, @PathVariable int size) {
        return service.read(userHash, number, size);
    }

    @Secured({UserRole.CUSTOMER_ROLE, UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @GetMapping("/all/{number}/{size}")
    Page<Spin> getAll(@PathVariable int number, @PathVariable int size) {
        return service.read(number, size);
    }

    @Secured({UserRole.API_CLIENT_ROLE})
    @PutMapping("/burn")
    List<SpinData> burn(String email, String userHash, int count) {
        return service.burn(email, userHash, count);
    }

    @Secured({UserRole.CUSTOMER_ROLE})
    @GetMapping("/one")
    SpinData one() {
        return service.readNotUsed(1);
    }

}
