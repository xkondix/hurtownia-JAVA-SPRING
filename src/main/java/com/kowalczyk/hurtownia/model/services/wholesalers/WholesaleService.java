package com.kowalczyk.hurtownia.model.services.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Wholesale;
import com.kowalczyk.hurtownia.model.repositories.employees.EmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.ProductRepository;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.WholesaleProductRepository;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.WholesaleRepository;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductQuantityRepresentationModel;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.WholesaleRepresentationModel;
import com.kowalczyk.hurtownia.model.resourceAssembler.ProductQuantityRepresentationModelAssembler;
import com.kowalczyk.hurtownia.model.responses.wholesalers.WholesaleRestModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WholesaleService {

    private final WholesaleRepository wholesaleRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final WholesaleProductRepository wholesaleProductRepository;

    public WholesaleService(WholesaleRepository wholesaleRepository, EmployeeRepository employeeRepository, ProductRepository productRepository, WholesaleProductRepository wholesaleProductRepository) {
        this.wholesaleRepository = wholesaleRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.wholesaleProductRepository = wholesaleProductRepository;
    }

    public List<WholesaleRepresentationModel> getAll() {
        return wholesaleRepository.findAll()
                .stream()
                .map(wholesale -> new WholesaleRepresentationModel(wholesale,getEmployees(wholesale.getId())))
                .collect(Collectors.toList());

    }

    public WholesaleRepresentationModel getById(Long id) {

        Optional<Wholesale> wholesale = wholesaleRepository.findById(id);
        return wholesale.isPresent() ? new WholesaleRepresentationModel(
                wholesale.get(),getEmployees(wholesale.get().getId())) : null;

    }

    public void saveWholesale(WholesaleRestModel wholesaleRestModel) {
        Wholesale wholesale = wholesaleRestModel.mapToEnity();
        wholesaleRepository.save(wholesale);

    }

    public List<ProductQuantityRepresentationModel> getAllProduct() {

        return productRepository.findAll().stream().map(
                product -> toProductRepresentationModel(product
                        ,getQuantityFromProduct(product)))
                .collect(Collectors.toList());

    }


    //methods

    private List<String> getEmployees(Long id)
    {
        return employeeRepository.findByWholesaleId(id).stream().map
                ( employee -> employee.getName() +" "+employee.getSurename())
                .collect(Collectors.toList());
    }



    private Long getQuantityFromProduct(
            Product product)
    {
       return wholesaleRepository.findAll().stream().map(
                wholesale -> wholesale.getProducts().stream()
                        .filter(wholesaleProduct -> wholesaleProduct.getProduct().getId()
                                .equals(product.getId()))
                        .findFirst())
               .collect(Collectors.toList())
               .stream().map(longVal ->
               {
                   if(longVal.isPresent())
                   {
                       return longVal.get().getQuantity();
                   }
                   else
                   {
                       return 0l;
                   }
               }).collect(Collectors.summingLong(Long::longValue));
    }

    private ProductQuantityRepresentationModel toProductRepresentationModel(
            Product product, Long quantiti)
    {
            return new ProductQuantityRepresentationModelAssembler
                    ("product",quantiti).toModel(product);
    }
}
