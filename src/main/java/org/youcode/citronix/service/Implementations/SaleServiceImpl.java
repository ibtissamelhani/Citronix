package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.domain.entities.Sale;
import org.youcode.citronix.repository.SaleRepository;
import org.youcode.citronix.service.HarvestService;
import org.youcode.citronix.service.SaleService;
import org.youcode.citronix.exception.Harvest.HarvestAlreadySoldException;
import org.youcode.citronix.exception.InvalidCredentialsException;
import org.youcode.citronix.exception.Sale.SaleNotFoundException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private HarvestService harvestService;

    @Transactional
    @Override
    public Sale createSale(UUID harvestId, Sale sale) {
        Harvest harvest = harvestService.findById(harvestId);
        boolean alreadySold = saleRepository.existsByHarvest(harvest);

        if (alreadySold){
            throw new HarvestAlreadySoldException("This harvest already has an associated sale.");
        }

        if(sale.getDate().isBefore(harvest.getDate())){
            throw new InvalidCredentialsException("Sale date cannot be earlier than the harvest date.");
        }
        sale.setHarvest(harvest);
        return saleRepository.save(sale);
    }

    @Override
    public Sale findById(UUID saleId) {
        if (saleId == null){
            throw new InvalidCredentialsException("sale Id is required");
        }
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found."));
    }

    @Override
    public void deleteSale(UUID saleId) {
        Sale sale = findById(saleId);
        saleRepository.delete(sale);
    }


}
