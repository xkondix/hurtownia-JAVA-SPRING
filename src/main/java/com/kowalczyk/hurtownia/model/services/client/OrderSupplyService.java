package com.kowalczyk.hurtownia.model.services.client;

import com.kowalczyk.hurtownia.model.entities.client.Client;
import com.kowalczyk.hurtownia.model.entities.client.OrderSupllyWholesaleProduct;
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
import java.util.concurrent.ConcurrentHashMap;
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

         Map<String,Long> order = orderSupplyRestModel.getProductsCount();

        while(order.size()>0) {

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
             Wholesale wholesale = wholesales.stream()
                     .collect(Collectors.toMap(Function.identity(),
                             x -> x.getProducts().stream()
                                     .filter(y -> y.count(orderSupplyRestModel.getProductsCount()))
                                     .count()))
                     .entrySet().stream()
                     .max((entry1, entry2) -> entry1.getValue() >= entry2.getValue()
                             ? 1 : -1).get().getKey();


             wholesales.remove(wholesale);
             List<WholesaleProduct> wholesaleProduct = wholesale.getProducts();

             order = order.entrySet().stream().filter( product -> {
                 Optional<WholesaleProduct> wholesaleProductOptional
                         = wholesaleProduct.stream().filter(
                         x -> x.getProduct().getNameOfProduct()
                                 .equals(product.getKey())).findFirst();


                 if(wholesaleProductOptional.isPresent())
                 {
                     wholesaleProductOptional.get().subtractProduct(product.getValue());
                     wholesaleProductRepository.save(wholesaleProductOptional.get());
                     orderSupplyWholesaleProductRepository.save(
                             new OrderSupllyWholesaleProduct(
                                     product.getValue(), wholesaleProductOptional.get(),orderSupply)
                     );
                     return false;
                 }
                 return true;

             }).collect(Collectors.toMap(
                     key -> key.getKey(),
                     val -> val.getValue()
             ));
            System.out.println(order);
         }




    }



    private void saveSupply(OrderSupply orderSupply
            , OrderSupplyRestModel orderSupplyRestModel) {
    }





}
