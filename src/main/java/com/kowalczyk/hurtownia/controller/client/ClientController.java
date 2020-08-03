package com.kowalczyk.hurtownia.controller.client;

import com.kowalczyk.hurtownia.model.responses.client.ClientRestModel;
import com.kowalczyk.hurtownia.model.services.client.ClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client")
    public void save(@RequestBody ClientRestModel clientRestModel)
    {
        clientService.save(clientRestModel);
    }

}
