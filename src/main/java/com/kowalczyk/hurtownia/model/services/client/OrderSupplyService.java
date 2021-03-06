package com.kowalczyk.hurtownia.model.services.client;

import com.kowalczyk.hurtownia.email.EmailService;
import com.kowalczyk.hurtownia.model.entities.client.Client;
import com.kowalczyk.hurtownia.model.entities.client.OrderSupllyWholesaleProduct;
import com.kowalczyk.hurtownia.model.entities.client.OrderSupply;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Wholesale;
import com.kowalczyk.hurtownia.model.entities.wholesalers.WholesaleProduct;
import com.kowalczyk.hurtownia.model.repositories.client.ClientRepository;
import com.kowalczyk.hurtownia.model.repositories.client.OrderSupplyRepository;
import com.kowalczyk.hurtownia.model.repositories.client.OrderSupplyWholesaleProductRepository;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.ProductRepository;
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
    private final ProductRepository productRepository;
    private final EmailService emailService;


    public OrderSupplyService(OrderSupplyWholesaleProductRepository orderSupplyWholesaleProductRepository
            , OrderSupplyRepository orderSupplyRepository
            , WholesaleProductRepository wholesaleProductRepository, ClientRepository clientRepository, WholesaleRepository wholesaleRepository, ProductRepository productRepository, EmailService emailService) {
        this.orderSupplyWholesaleProductRepository = orderSupplyWholesaleProductRepository;
        this.orderSupplyRepository = orderSupplyRepository;
        this.wholesaleProductRepository = wholesaleProductRepository;
        this.clientRepository = clientRepository;
        this.wholesaleRepository = wholesaleRepository;
        this.productRepository = productRepository;
        this.emailService = emailService;
    }


    public void save(OrderSupplyRestModel orderSupplyRestModel) throws NotFoundException {

        OrderSupply orderSupply = mapToEntity(orderSupplyRestModel);
        if(orderSupply==null)
        {
            throw new NullPointerException("bad data");
        }

        orderSupplyRepository.save(orderSupply);

        if (orderSupply.getTypeOfService().equals(ORDER)) {
            saveOrder(orderSupply, orderSupplyRestModel);
        }
        else{
            if(!orderSupply.getWholesale().equals(null)) {
                saveSupply(orderSupply, orderSupplyRestModel);
            }
            else
            {
                throw new NotFoundException("not found wholesale id");
            }
        }

    }



    //methods
    private OrderSupply mapToEntity(OrderSupplyRestModel orderSupplyRestModel)
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


        final Map<Product,Long> wholesaleProductEmail = orderSupplyRestModel.getProductsCount()
                .entrySet().stream()
                .map(product -> productRepository.findByNameOfProduct(product.getKey()).get()).collect(Collectors.toMap(
                        key -> key
                        ,value -> orderSupplyRestModel.getProductsCount().get(value.getNameOfProduct())));
        List<Wholesale> wholesales = wholesaleRepository.findAll();
        Map<String,Long> order = orderSupplyRestModel.getProductsCount();
        int i = wholesales.size();


        while(order.size()>0 && i>0 ){

            Wholesale wholesale = takeTheWholesaleWithTheLargestQuantityOfProducts(
                    checkWhichWholesaleHasTheMostProductsFromTheOrder(wholesales,orderSupplyRestModel));


            wholesales.remove(wholesale);
            List<WholesaleProduct> wholesaleProducts = wholesale.getProducts();

            order = order.entrySet().stream().filter( product -> {
                Optional<WholesaleProduct> wholesaleProductOptional
                        = wholesaleProducts.stream().filter(
                        x -> (x.getProduct().getNameOfProduct().equals(product.getKey())
                        )).findFirst();


                if(wholesaleProductOptional.isPresent())
                {
                    Long quanity = saveOrderWholesaleProduct(wholesaleProductOptional.get()
                            ,product.getValue(),orderSupply);
                    if(quanity==0) {
                        return false;
                    }
                    else
                    {
                        product.setValue(quanity);
                        return true;
                    }
                }
                return true;

            }).collect(Collectors.toMap(
                    key -> key.getKey(),
                    val -> val.getValue()
            ));

            i--;
        }

        sendEmail(wholesaleProductEmail,orderSupply,
                clientRepository.findById(orderSupplyRestModel.getClientid()).get());

    }



    private void saveSupply(OrderSupply orderSupply
            , OrderSupplyRestModel orderSupplyRestModel) {

        Optional<Wholesale> wholesale = wholesaleRepository
                .findById(orderSupplyRestModel.getWholesaleId());


        if(wholesale.isPresent())
        {
            List<WholesaleProduct> wholesaleProducts = wholesale.get().getProducts();
            Map<String,Long> supply = orderSupplyRestModel.getProductsCount();
            supply.entrySet().forEach( product -> {
                Optional<WholesaleProduct> wholesaleProductOptional
                        = wholesaleProducts.stream().filter(
                        x -> x.getProduct().getNameOfProduct()
                                .equals(product.getKey())).findFirst();


                if(wholesaleProductOptional.isPresent())
                {
                    saveSupplyWholesaleProuct(wholesaleProductOptional.get()
                            ,product.getValue(),orderSupply);
                }
                else
                {
                    createWholesaleProduct(product.getKey(),product.getValue(),wholesale.get());
                }

            });
        }

    }

    private void saveSupplyWholesaleProuct(WholesaleProduct wholesaleProduct
            ,Long product, OrderSupply orderSupply)
    {
        wholesaleProduct.addProduct(product);
        wholesaleProductRepository.save(wholesaleProduct);
        orderSupplyWholesaleProductRepository.save(
                new OrderSupllyWholesaleProduct(
                        product, wholesaleProduct,orderSupply));
    }

    private Map<Wholesale,Long> checkWhichWholesaleHasTheMostProductsFromTheOrder(List<Wholesale> wholesales
            , OrderSupplyRestModel orderSupplyRestModel)
    {
        return wholesales.stream()
                .collect(Collectors.toMap(Function.identity(),
                        x -> x.getProducts().stream()
                                .filter(y -> y.count(orderSupplyRestModel.getProductsCount()))
                                .count()));
    }

    private Wholesale takeTheWholesaleWithTheLargestQuantityOfProducts(Map<Wholesale,Long> map)
    {
        return map.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() >= entry2.getValue() ? 1 : -1).get().getKey();
    }

    private Long saveOrderWholesaleProduct(WholesaleProduct wholesaleProduct, Long product, OrderSupply orderSupply)
    {
        Long quanity = 0l;

        if(product>wholesaleProduct.getQuantity())
        {
            quanity = product-wholesaleProduct.getQuantity();
            wholesaleProduct.subtractProduct(wholesaleProduct.getQuantity());

        }
        else {
            wholesaleProduct.subtractProduct(product);
        }
        wholesaleProductRepository.save(wholesaleProduct);
        orderSupplyWholesaleProductRepository.save(
                new OrderSupllyWholesaleProduct(
                        quanity==0 ? product : product-quanity
                        ,wholesaleProduct, orderSupply));


        return quanity;
    }

    private void createWholesaleProduct(String name,Long value, Wholesale wholesale)
    {
        Optional<Product> product = productRepository.findByNameOfProduct(name);
        if(product.isPresent())
        {
            wholesaleProductRepository.save(new WholesaleProduct(value,product.get(),wholesale));
        }
    }

    public void sendEmail(Map<Product, Long> wholesaleProductEmail
            , OrderSupply orderSupply, Client client)
    {
        String content =
                emailService.createContent(wholesaleProductEmail,orderSupply,client);
        emailService.sendEmail(client.getContactDetails().getEmail()
                ,"Your Order",content);

    }



}