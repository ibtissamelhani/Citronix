package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.domain.entities.Sale;
import org.youcode.citronix.repository.SaleRepository;
import org.youcode.citronix.service.HarvestService;
import org.youcode.citronix.service.SaleService;
import org.youcode.citronix.web.exception.InvalidCredentialsException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private HarvestService harvestService;

    @Override
    public Sale createSale(UUID harvestId, Sale sale) {
        Harvest harvest = harvestService.findById(harvestId);

        if (sale.getQuantity() > harvest.getTotalQuantity()) {
            throw new InvalidCredentialsException("Sale quantity exceeds available harvest quantity.");
        }

        sale.setHarvest(harvest);
        Sale savedSale = saleRepository.save(sale);

        return savedSale;
    }


}
