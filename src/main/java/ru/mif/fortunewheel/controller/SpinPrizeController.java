package ru.mif.fortunewheel.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.dto.Page;
import ru.mif.fortunewheel.dto.data.SpinPrizeData;
import ru.mif.fortunewheel.enums.UserRole;

@RestController
@RequestMapping("/spin-prize")
public class SpinPrizeController {
    @Secured({UserRole.CUSTOMER_ROLE})
    @PostMapping
    SpinPrizeData create(long spinId){
        return null;
    }

    @Secured({UserRole.CUSTOMER_ROLE})
    @GetMapping
    Page<SpinPrizeData> my(){
        return  null;
    }

    @Secured({UserRole.CUSTOMER_ROLE,UserRole.API_CLIENT_ROLE})
    @GetMapping("/by-user/:userId")
    Page<SpinPrizeData> byUser(long userId){
        return null;
    }

    @Secured({UserRole.CUSTOMER_ROLE,UserRole.ADMIN_ROLE,UserRole.API_CLIENT_ROLE})
    @GetMapping("/by-spin/:spinId")
    SpinPrizeData bySpin(long spinId){
        return null;
    }

    @Secured({UserRole.ADMIN_ROLE,UserRole.API_CLIENT_ROLE})
    @PatchMapping("/give/:id")
    SpinPrizeData give(long id){
        return null;
    }
}
