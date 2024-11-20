package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.DTO.Tree.TreeRequestDTO;
import org.youcode.citronix.domain.entities.Sale;
import org.youcode.citronix.service.SaleService;

@RestController
@RequestMapping("v1/api/sales")
@AllArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/save")
    public ResponseEntity<Sale> saveTree(@RequestBody @Valid TreeRequestDTO treeRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body();
    }


}
