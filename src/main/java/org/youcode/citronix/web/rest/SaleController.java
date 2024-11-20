package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.domain.entities.Sale;
import org.youcode.citronix.service.SaleService;
import org.youcode.citronix.web.VM.Sale.SaleCreationVm;
import org.youcode.citronix.web.VM.mapper.SaleCreationVMMapper;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/sales")
@AllArgsConstructor
public class SaleController {

    private final SaleService saleService;
    private final SaleCreationVMMapper saleCreationVMMapper;

    @PostMapping("/save/{harvestId}")
    public ResponseEntity<Sale> saveTree(@PathVariable UUID harvestId, @RequestBody @Valid SaleCreationVm saleCreationVm) {
        Sale saleToSave = saleCreationVMMapper.toSale(saleCreationVm);
        Sale savedSale = saleService.createSale(harvestId,saleToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSale);
    }


}
