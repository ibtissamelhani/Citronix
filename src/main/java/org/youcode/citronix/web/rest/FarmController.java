package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.service.FarmService;
import org.youcode.citronix.web.VM.Farm.FarmRequestVM;
import org.youcode.citronix.web.VM.mapper.FarmRequestVMMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/farms")
@AllArgsConstructor
public class FarmController {

    private final FarmService farmService;
    private final FarmRequestVMMapper farmRequestVMMapper;

    @PostMapping("/save")
    public ResponseEntity<Farm> saveFarm(@RequestBody @Valid FarmRequestVM farmRequestVM) {
        Farm farmToSave = farmRequestVMMapper.toFarm(farmRequestVM);
        Farm savedFarm = farmService.save(farmToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFarm);
    }

    @GetMapping
    public ResponseEntity<List<Farm>> getAllFarms() {
        List<Farm> farms = farmService.getAllFarms();
        return ResponseEntity.ok(farms);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Farm>> getFarmsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Farm> farmPage = farmService.getFarmsWithPagination(page,size);
        return ResponseEntity.ok(farmPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarmById(@PathVariable UUID id) {
        return ResponseEntity.ok(farmService.getFarmById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Farm> updateFarm(@RequestParam UUID id, @Valid @RequestBody FarmRequestVM farmRequestVM) {
        Farm farmToUpdate = farmRequestVMMapper.toFarm(farmRequestVM);
        Farm updatedFarm = farmService.updateFarm(id, farmToUpdate);
        return ResponseEntity.ok(updatedFarm);
    }
}
