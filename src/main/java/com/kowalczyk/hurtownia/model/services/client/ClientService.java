package com.kowalczyk.hurtownia.model.services.client;

import com.kowalczyk.hurtownia.model.entities.client.Address;
import com.kowalczyk.hurtownia.model.entities.client.Client;
import com.kowalczyk.hurtownia.model.entities.client.ContactDetails;
import com.kowalczyk.hurtownia.model.repositories.client.AddressRepository;
import com.kowalczyk.hurtownia.model.repositories.client.ClientRepository;
import com.kowalczyk.hurtownia.model.repositories.client.ContactDetailsRepository;
import com.kowalczyk.hurtownia.model.responses.client.ClientRestModel;
import org.springframework.stereotype.Service;

import static com.kowalczyk.hurtownia.model.entities.client.Client.TypeOfClient.COMPANY;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ContactDetailsRepository contactDetailsRepository;

    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository, ContactDetailsRepository contactDetailsRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.contactDetailsRepository = contactDetailsRepository;
    }


    public void save(ClientRestModel clientRestModel)
    {
        Address address =clientRestModel.mapToEnityAddres();
        ContactDetails contactDetails = clientRestModel.mapToEnityContactDetails();
        Client client = new Client(clientRestModel.getTypeOfClient(), address, contactDetails);
        addressRepository.save(address);
        contactDetailsRepository.save(contactDetails);
        clientRepository.save(client);
    }
}
