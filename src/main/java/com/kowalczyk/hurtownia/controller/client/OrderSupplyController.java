package com.kowalczyk.hurtownia.controller.client;

import com.kowalczyk.hurtownia.model.responses.client.OrderSupplyRestModel;
import com.kowalczyk.hurtownia.model.services.client.ClientService;
import com.kowalczyk.hurtownia.model.services.client.OrderSupplyService;
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
    public void saveOrder(@RequestBody OrderSupplyRestModel orderSupplyRestModel)
    {
        orderSupplyService.save(orderSupplyRestModel);
    }

    @PostMapping("/supply")
    public void saveSupply(@RequestBody OrderSupplyRestModel orderSupplyRestModel)
    {
        orderSupplyService.save(orderSupplyRestModel);
    }

}
