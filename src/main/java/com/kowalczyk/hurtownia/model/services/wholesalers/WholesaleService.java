package com.kowalczyk.hurtownia.model.services.wholesalers;

import com.kowalczyk.hurtownia.model.repositories.wholesalers.WholesaleRepository;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.WholesaleRepresentationModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WholesaleService {

    private final WholesaleRepository wholesaleRepository;

    public WholesaleService(WholesaleRepository wholesaleRepository) {
        this.wholesaleRepository = wholesaleRepository;
    }

    public List<WholesaleRepresentationModel> getAll() {
        return wholesaleRepository.findAll()
                .stream()
                .map(wholesale -> new WholesaleRepresentationModel(wholesale))
                .collect(Collectors.toList());

    }
}
