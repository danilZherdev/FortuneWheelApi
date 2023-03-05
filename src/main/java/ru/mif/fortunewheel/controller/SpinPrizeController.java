package ru.mif.fortunewheel.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.dto.data.SpinPrizeData;
import ru.mif.fortunewheel.service.access.SpinPrizeService;

@RestController
@RequestMapping("/spin-prize")
public class SpinPrizeController {

    private final SpinPrizeService service;

    public SpinPrizeController(SpinPrizeService service) {
        this.service = service;
    }

    //    @Secured({UserRole.CUSTOMER_ROLE})
    @PostMapping
    SpinPrizeData create(long spinId){
        return service.create(spinId);
    }

    //    @Secured({UserRole.CUSTOMER_ROLE})
    @GetMapping
    Page<SpinPrizeData> my(int number, int size){
        return service.read("my_hash", number, size);
    }

    //    @Secured({UserRole.CUSTOMER_ROLE,UserRole.API_CLIENT_ROLE})
    @GetMapping("/by-user/{userId}")
    Page<SpinPrizeData> byUser(long userId, int number, int size){
        return service.read("my_hash", number, size);
    }

    //    @Secured({UserRole.ADMIN_ROLE,UserRole.API_CLIENT_ROLE})
    @PatchMapping("/give/{id}")
    SpinPrizeData give(long id){
        return null;
    }
}
