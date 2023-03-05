package ru.mif.fortunewheel.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.dto.Page;
import ru.mif.fortunewheel.dto.data.SpinData;
import ru.mif.fortunewheel.enums.UserRole;

import java.util.List;

@RestController
@RequestMapping("/spin")
public class SpinController {

    @Secured({UserRole.API_CLIENT_ROLE})
    @PostMapping()
    List<SpinData> create(String userHash, int count) {
        return null;
    }

    @Secured({UserRole.CUSTOMER_ROLE})
    @GetMapping("/count")
    int notUsedCount() {
        return 0;
    }

    @Secured({UserRole.CUSTOMER_ROLE, UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @GetMapping("/all")
    Page<SpinData> getAll(@PathVariable int size, @PathVariable int number, String userHash) {
        return null;
    }

    @Secured({UserRole.API_CLIENT_ROLE})
    @PutMapping("/burn")
    List<SpinData> burn(String userHash, int count) {
        return null;
    }

    @Secured({UserRole.CUSTOMER_ROLE})
    @GetMapping("/one")
    List<SpinData> one() {
        return null;
    }

}
