package com.kowalczyk.hurtownia.controller.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Wholesale;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.WholesaleRepresentationModel;
import com.kowalczyk.hurtownia.model.services.wholesalers.WholesaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class WholesaleController {

    private final WholesaleService wholesaleService;

    public WholesaleController(WholesaleService wholesaleService) {
        this.wholesaleService = wholesaleService;
    }

    @GetMapping("wholesale")
    public List<WholesaleRepresentationModel> getAll()
    {
        return wholesaleService.getAll();
    }

    @GetMapping("wholesale/{id}")
    public WholesaleRepresentationModel getById(@PathVariable Long id)
    {
        return wholesaleService.getById(id);
    }


}
