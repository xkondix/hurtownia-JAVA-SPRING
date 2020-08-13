package com.kowalczyk.hurtownia.controller.client;

import com.kowalczyk.hurtownia.model.responses.client.ClientRestModel;
import com.kowalczyk.hurtownia.model.services.client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> save(@RequestBody ClientRestModel clientRestModel)
    {
        try{
            clientService.save(clientRestModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }

    }


}
