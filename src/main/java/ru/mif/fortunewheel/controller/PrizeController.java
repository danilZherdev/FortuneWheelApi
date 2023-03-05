package ru.mif.fortunewheel.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.dto.Page;
import ru.mif.fortunewheel.dto.data.PrizeData;
import ru.mif.fortunewheel.dto.data.UserData;
import ru.mif.fortunewheel.dto.models.PrizeModel;
import ru.mif.fortunewheel.enums.UserRole;

import java.util.List;

@RestController
@RequestMapping("/prize")
public class PrizeController {

    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @PostMapping
    public PrizeData create(PrizeModel prizeModel){
        return null;
    }

    @Secured({UserRole.ADMIN_ROLE, UserRole.CUSTOMER_ROLE,UserRole.API_CLIENT_ROLE})
    @GetMapping("possible")
    List<PrizeData> possible(List<PrizeModel> prizeModels){
        return null;
    }

    @PostMapping
    public PrizeData update(PrizeModel model) {
        return null;
    }

    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @DeleteMapping("/id")
    public PrizeData remove(@PathVariable long id){
        return null;
    }

    @Secured({UserRole.ADMIN_ROLE,UserRole.API_CLIENT_ROLE})
    @GetMapping("{/:size/:number}")
    public Page<UserData> get(@PathVariable int size, @PathVariable int number){
        return null;
    }

}
