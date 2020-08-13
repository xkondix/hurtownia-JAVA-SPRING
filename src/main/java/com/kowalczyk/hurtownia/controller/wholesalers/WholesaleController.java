package com.kowalczyk.hurtownia.controller.wholesalers;

import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductQuantityRepresentationModel;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductRepresentationModel;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.WholesaleRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.wholesalers.WholesaleRestModel;
import com.kowalczyk.hurtownia.model.services.wholesalers.WholesaleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<WholesaleRepresentationModel>> getAll()
    {
        return ResponseEntity.ok(
                wholesaleService.getAll());
    }

    @GetMapping("wholesale/{id}")
    public ResponseEntity<WholesaleRepresentationModel> getById(@PathVariable Long id)
    {
        WholesaleRepresentationModel wholesaleRepresentationModel
                = wholesaleService.getById(id);
        if(wholesaleRepresentationModel==null)
        {
            return new ResponseEntity<WholesaleRepresentationModel>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(wholesaleRepresentationModel);
    }

    @PostMapping("wholesale")
    public ResponseEntity<?> saveWholesale(@RequestBody WholesaleRestModel wholesaleRestModel)
    {
        try{
            wholesaleService.saveWholesale(wholesaleRestModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("wholesale/getAllProduct")
    public ResponseEntity<CollectionModel<ProductQuantityRepresentationModel>> getAllProduct()
    {
        return ResponseEntity.ok(CollectionModel.of(
                wholesaleService.getAllProduct(),
                linkTo(methodOn(WholesaleController.class).getAll()).withSelfRel()));
    }



}
