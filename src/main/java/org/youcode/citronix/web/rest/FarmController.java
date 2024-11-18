package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.service.FarmService;
import org.youcode.citronix.web.VM.Farm.FarmCreationVM;
import org.youcode.citronix.web.VM.mapper.FarmCreationVMMapper;

import java.util.List;

@RestController
@RequestMapping("v1/api/farms")
@AllArgsConstructor
public class FarmController {

    private final FarmService farmService;
    private final FarmCreationVMMapper farmCreationVMMapper;

    @PostMapping("/save")
    public ResponseEntity<Farm> saveFarm(@RequestBody @Valid FarmCreationVM farmCreationVM) {
        Farm farmToSave = farmCreationVMMapper.toFarm(farmCreationVM);
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
}
