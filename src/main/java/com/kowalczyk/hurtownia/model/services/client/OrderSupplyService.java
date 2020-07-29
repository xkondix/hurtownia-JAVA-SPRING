package com.kowalczyk.hurtownia.model.services.client;

import com.kowalczyk.hurtownia.model.entities.client.Client;
import com.kowalczyk.hurtownia.model.entities.client.OrderSupply;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Wholesale;
import com.kowalczyk.hurtownia.model.entities.wholesalers.WholesaleProduct;
import com.kowalczyk.hurtownia.model.repositories.client.ClientRepository;
import com.kowalczyk.hurtownia.model.repositories.client.OrderSupplyRepository;
import com.kowalczyk.hurtownia.model.repositories.client.OrderSupplyWholesaleProductRepository;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.WholesaleProductRepository;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.WholesaleRepository;
import com.kowalczyk.hurtownia.model.responses.client.OrderSupplyRestModel;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.kowalczyk.hurtownia.model.entities.client.OrderSupply.TypeOfService.ORDER;

@Service
public class OrderSupplyService {

    private final OrderSupplyWholesaleProductRepository orderSupplyWholesaleProductRepository;
    private final OrderSupplyRepository orderSupplyRepository;
    private final WholesaleProductRepository wholesaleProductRepository;
    private final ClientRepository clientRepository;
    private final WholesaleRepository wholesaleRepository;


    public OrderSupplyService(OrderSupplyWholesaleProductRepository orderSupplyWholesaleProductRepository
            , OrderSupplyRepository orderSupplyRepository
            , WholesaleProductRepository wholesaleProductRepository, ClientRepository clientRepository, WholesaleRepository wholesaleRepository) {
        this.orderSupplyWholesaleProductRepository = orderSupplyWholesaleProductRepository;
        this.orderSupplyRepository = orderSupplyRepository;
        this.wholesaleProductRepository = wholesaleProductRepository;
        this.clientRepository = clientRepository;
        this.wholesaleRepository = wholesaleRepository;
    }


    public void save(OrderSupplyRestModel orderSupplyRestModel) {

        OrderSupply orderSupply = mapToEntity(orderSupplyRestModel);
        if(orderSupply.equals(null))
        {
            try {
                throw new NotFoundException("not found");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        orderSupplyRepository.save(orderSupply);

        if (orderSupply.getTypeOfService().equals(ORDER)) {
            saveOrder(orderSupply, orderSupplyRestModel);
        }
        else{
            saveSupply(orderSupply, orderSupplyRestModel);
        }

    }



    //methods
    public OrderSupply mapToEntity(OrderSupplyRestModel orderSupplyRestModel)
    {
        Optional<Client> client = clientRepository.findById(orderSupplyRestModel.getClientid());
        if(client.isPresent()) {
            return new OrderSupply(orderSupplyRestModel.getType()
                    , orderSupplyRestModel.getWholesaleId()
                    , clientRepository.findById(orderSupplyRestModel.getClientid()).get());
        }
        return null;
    }

    private void saveOrder(OrderSupply orderSupply
            , OrderSupplyRestModel orderSupplyRestModel) {

         List<Wholesale> wholesales = wholesaleRepository.findAll();

        Map<Wholesale,Long> algorytm = wholesales.stream()
                .collect(Collectors.toMap(Function.identity(),
                        x -> x.getProducts().stream()
                                .filter(y -> y.count(orderSupplyRestModel.getProductsCount()))
                                .count()));

       wholesales.stream().forEach(System.out::println);


    }



    private void saveSupply(OrderSupply orderSupply
            , OrderSupplyRestModel orderSupplyRestModel) {
    }





}
