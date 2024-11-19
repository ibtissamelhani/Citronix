package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.DTO.Farm.SearchFarmDTO;
import org.youcode.citronix.service.FarmService;
import org.youcode.citronix.web.VM.Farm.FarmReqVM2;
import org.youcode.citronix.web.VM.Farm.FarmRequestVM;
import org.youcode.citronix.web.VM.mapper.FarmReqVMMapper;
import org.youcode.citronix.web.VM.mapper.FarmRequestVMMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/farms")

public class FarmController {

    private final FarmService farmService;
    private final FarmRequestVMMapper farmRequestVMMapper;
    private final FarmReqVMMapper farmReqVMMapper;

    public FarmController(@Qualifier("farmServiceImpl2") FarmService farmService, FarmRequestVMMapper farmRequestVMMapper, FarmReqVMMapper farmReqVMMapper) {
        this.farmService = farmService;
        this.farmRequestVMMapper = farmRequestVMMapper;
        this.farmReqVMMapper = farmReqVMMapper;
    }

    //    @PostMapping("/save")
//    public ResponseEntity<Farm> saveFarm(@RequestBody @Valid FarmRequestVM farmRequestVM) {
//        Farm farmToSave = farmRequestVMMapper.toFarm(farmRequestVM);
//        Farm savedFarm = farmService.save(farmToSave);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedFarm);
//    }

    @PostMapping("/save")
    public ResponseEntity<Farm> saveFarm(@RequestBody @Valid FarmReqVM2 farmReqVM2) {
        Farm farmToSave = farmReqVMMapper.toFarm(farmReqVM2);
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

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFarm(@RequestParam UUID id) {
        farmService.deleteFarm(id);
        return ResponseEntity.ok("Farm deleted successfully.");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Farm>> search(@RequestBody SearchFarmDTO searchFarmDTO){
        List<Farm> farmList= farmService.search(searchFarmDTO);
        return ResponseEntity.ok().body(farmList);
    }
}
