package ru.mif.fortunewheel.controller;

import org.springframework.data.domain.Page;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.domain.Prize;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.data.PrizeData;
import ru.mif.fortunewheel.dto.models.PrizeModel;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.service.access.PrizeService;

import java.util.List;

@RestController
@RequestMapping("/prize")
public class PrizeController {

    private final PrizeService service;

    public PrizeController(PrizeService service) {
        this.service = service;
    }

//    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @PostMapping
    public PrizeData create(PrizeModel prizeModel){
        return (PrizeData) service.create(prizeModel);
    }

//    @Secured({UserRole.ADMIN_ROLE, UserRole.CUSTOMER_ROLE,UserRole.API_CLIENT_ROLE})
    @GetMapping("possible")
    List<PrizeData> possible(){
        return List.of();
    }

    @PutMapping
    public PrizeData update(PrizeModel model) {
        return (PrizeData) service.update(model);
    }

//    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @DeleteMapping("/{id}")
    public PrizeData remove(@PathVariable long id){
        return (PrizeData) service.delete(id);
    }

//    @Secured({UserRole.ADMIN_ROLE,UserRole.API_CLIENT_ROLE})
    @GetMapping("/{size}/{number}")
    public Page<Data<Prize>> get(@PathVariable int size, @PathVariable int number){
        return service.read(size, number);
    }

}
