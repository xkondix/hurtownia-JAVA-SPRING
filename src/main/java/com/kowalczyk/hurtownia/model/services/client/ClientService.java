package com.kowalczyk.hurtownia.model.services.client;

import com.kowalczyk.hurtownia.model.entities.client.Client;
import com.kowalczyk.hurtownia.model.repositories.client.ClientRepository;
import org.springframework.stereotype.Service;

import static com.kowalczyk.hurtownia.model.entities.client.Client.TypeOfClient.COMPANY;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save()
    {
        clientRepository.save(new Client(COMPANY));
    }
}
