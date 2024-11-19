package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.service.HarvestService;
import org.youcode.citronix.web.VM.Harvest.HarvestCreationVM;
import org.youcode.citronix.web.VM.Harvest.HarvestResponseVM;
import org.youcode.citronix.web.VM.mapper.HarvestCreationVMMapper;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/harvest")
@AllArgsConstructor
public class HarvestController {

    private final HarvestService harvestService;
    private final HarvestCreationVMMapper harvestCreationVMMapper;

    @PostMapping("/{fieldId}")
    public ResponseEntity<HarvestResponseVM> createHarvest(
            @PathVariable UUID fieldId,
            @Valid @RequestBody HarvestCreationVM harvestCreationVM) {
        Harvest harvest = harvestCreationVMMapper.toHarvest(harvestCreationVM);
        Harvest createdHarvest = harvestService.createHarvest(fieldId,harvest);

        return ResponseEntity.status(HttpStatus.CREATED).body(HarvestResponseVM.fromEntity(createdHarvest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHarvest(@PathVariable UUID id) {
        harvestService.delete(id);
        return ResponseEntity.ok("harvest deleted successfully");
    }



}
