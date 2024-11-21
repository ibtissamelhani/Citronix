package org.youcode.citronix.service;

import org.youcode.citronix.DTO.SaleDTO;
import org.youcode.citronix.domain.entities.Sale;

import java.util.UUID;

public interface SaleService {
    SaleDTO createSale(UUID harvestId, Sale sale);
    void deleteSale(UUID saleId);
    Sale findById(UUID saleId);
}
