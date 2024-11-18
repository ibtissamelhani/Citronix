package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.service.FarmService;
import org.youcode.citronix.web.VM.Farm.FarmCreationVM;
import org.youcode.citronix.web.VM.mapper.FarmCreationVMMapper;

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
}
