package ru.mif.fortunewheel.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.mif.fortunewheel.domain.Prize;
import ru.mif.fortunewheel.dto.Page;
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

    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @PostMapping
    public PrizeData create(@RequestBody PrizeModel prizeModel){
        return (PrizeData) service.create(prizeModel);
    }

    @Secured({UserRole.ADMIN_ROLE, UserRole.CUSTOMER_ROLE, UserRole.API_CLIENT_ROLE})
    @GetMapping("/possible")
    List<PrizeData> possible(){
        return List.of();
    }

    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @PutMapping
    public PrizeData update(@RequestBody PrizeModel model) {
        return (PrizeData) service.update(model);
    }

    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @DeleteMapping("/{id}")
    public PrizeData remove(@PathVariable long id){
        return (PrizeData) service.delete(id);
    }

    @Secured({UserRole.ADMIN_ROLE, UserRole.API_CLIENT_ROLE})
    @GetMapping("/all/{number}/{size}")
    public Page<Prize> get(@PathVariable int number, @PathVariable int size){
        return service.read(number, size);
    }

}
