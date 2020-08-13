package com.kowalczyk.hurtownia.controller.client;

import com.kowalczyk.hurtownia.model.responses.client.OrderSupplyRestModel;
import com.kowalczyk.hurtownia.model.services.client.ClientService;
import com.kowalczyk.hurtownia.model.services.client.OrderSupplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class OrderSupplyController {

    private final OrderSupplyService orderSupplyService;
    private final ClientService client;

    public OrderSupplyController(OrderSupplyService orderSupplyService, ClientService client) {
        this.orderSupplyService = orderSupplyService;
        this.client = client;
    }

    @PostMapping("/order")
    public ResponseEntity<?> saveOrder(@RequestBody OrderSupplyRestModel orderSupplyRestModel)
    {
        try{
            orderSupplyService.save(orderSupplyRestModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/supply")
    public ResponseEntity<?> saveSupply(@RequestBody OrderSupplyRestModel orderSupplyRestModel)
    {
        try{
            orderSupplyService.save(orderSupplyRestModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }

}
