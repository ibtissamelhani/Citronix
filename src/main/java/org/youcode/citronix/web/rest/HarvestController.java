package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.domain.enums.Season;
import org.youcode.citronix.service.HarvestService;
import org.youcode.citronix.web.VM.Harvest.HarvestCreationVM;
import org.youcode.citronix.web.VM.Harvest.HarvestResponseVM;
import org.youcode.citronix.web.VM.mapper.HarvestVMMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/harvest")
@AllArgsConstructor
public class HarvestController {

    private final HarvestService harvestService;
    private final HarvestVMMapper harvestVMMapper;

    @PostMapping("/{fieldId}")
    public ResponseEntity<HarvestResponseVM> createHarvest(
            @PathVariable UUID fieldId,
            @Valid @RequestBody HarvestCreationVM harvestCreationVM) {
        Harvest harvest = harvestVMMapper.toHarvest(harvestCreationVM);
        Harvest createdHarvest = harvestService.createHarvest(fieldId,harvest);

        return ResponseEntity.status(HttpStatus.CREATED).body(harvestVMMapper.toHarvestResponseVM(createdHarvest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHarvest(@PathVariable UUID id) {
        harvestService.delete(id);
        return ResponseEntity.ok("harvest deleted successfully");
    }

    @GetMapping("/season/{season}")
    public ResponseEntity<List<HarvestResponseVM>> getHarvestsBySeason(@PathVariable Season season) {
        List<Harvest> harvests = harvestService.getHarvestsBySeason(season);
        List<HarvestResponseVM> response = harvests.stream()
                .map(harvestVMMapper::toHarvestResponseVM)
                .toList();
        return ResponseEntity.ok(response);
    }

}
