package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.domain.entities.Sale;
import org.youcode.citronix.repository.SaleRepository;
import org.youcode.citronix.service.HarvestService;
import org.youcode.citronix.service.SaleService;
import org.youcode.citronix.web.exception.Harvest.HarvestAlreadySoldException;

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

        sale.setHarvest(harvest);
        return saleRepository.save(sale);
    }

//    public double calculateTotalRevenue(UUID harvestId) {
//        return saleRepository.findByHarvestId(harvestId)
//                .stream()
//                .mapToDouble(Sale::calculateRevenue)
//                .sum();
//    }

}
