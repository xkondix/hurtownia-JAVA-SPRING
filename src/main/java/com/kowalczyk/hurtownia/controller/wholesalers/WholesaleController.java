package com.kowalczyk.hurtownia.controller.wholesalers;

import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductQuantityRepresentationModel;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductRepresentationModel;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.WholesaleRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.wholesalers.WholesaleRestModel;
import com.kowalczyk.hurtownia.model.services.wholesalers.WholesaleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @PostMapping("wholesale")
    public void saveWholesale(@RequestBody WholesaleRestModel wholesaleRestModel)
    {
        wholesaleService.saveWholesale(wholesaleRestModel);
    }

    @GetMapping("wholesale/getAllProduct")
    public CollectionModel<ProductQuantityRepresentationModel> getAllProduct()
    {
        return CollectionModel.of(
                wholesaleService.getAllProduct(),
                linkTo(methodOn(ProductController.class).getAll()).withSelfRel());
    }



}
