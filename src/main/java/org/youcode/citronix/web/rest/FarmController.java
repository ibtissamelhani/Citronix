package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.DTO.SearchFarmDTO;
import org.youcode.citronix.service.FarmService;
import org.youcode.citronix.web.VM.Farm.FarmRequestVM;
import org.youcode.citronix.web.VM.Farm.FarmResponseVM;
import org.youcode.citronix.web.VM.mapper.FarmVMMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/farms")
@AllArgsConstructor
public class FarmController {

    private final FarmService farmService;
    private final FarmVMMapper farmVMMapper;

    @PostMapping("/save")
    public ResponseEntity<FarmResponseVM> saveFarm(@RequestBody @Valid FarmRequestVM farmRequestVM) {
        Farm farmToSave = farmVMMapper.toFarm(farmRequestVM);
        Farm savedFarm = farmService.save(farmToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(farmVMMapper.toFarmResponseVM(savedFarm));
    }

    @GetMapping
    public ResponseEntity<List<FarmResponseVM>> getAllFarms() {
        List<Farm> farms = farmService.getAllFarms();
        List<FarmResponseVM> farmResponseVMS = farms.stream()
                .map(farmVMMapper::toFarmResponseVM)
                .toList();
        return ResponseEntity.ok(farmResponseVMS);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<FarmResponseVM>> getFarmsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Farm> farmPage = farmService.getFarmsWithPagination(page,size);
        List<FarmResponseVM> farmResponseVMList = farmPage.stream().map(farmVMMapper::toFarmResponseVM).toList();
        Page<FarmResponseVM> farmResponseVMPage = new PageImpl<>(farmResponseVMList, farmPage.getPageable(), farmPage.getTotalElements());

        return ResponseEntity.ok(farmResponseVMPage);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<FarmResponseVM> getFarmById(@PathVariable UUID id) {
        Farm farm = farmService.getFarmById(id);
        return ResponseEntity.ok(farmVMMapper.toFarmResponseVM(farm));
    }

    @PutMapping("/update")
    public ResponseEntity<FarmResponseVM> updateFarm(@RequestParam UUID id, @Valid @RequestBody FarmRequestVM farmRequestVM) {
        Farm farmToUpdate = farmVMMapper.toFarm(farmRequestVM);
        Farm updatedFarm = farmService.updateFarm(id, farmToUpdate);
        return ResponseEntity.ok(farmVMMapper.toFarmResponseVM(updatedFarm));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFarm(@RequestParam UUID id) {
        farmService.deleteFarm(id);
        return ResponseEntity.ok("Farm deleted successfully.");
    }

    @GetMapping("/search")
    public ResponseEntity<List<FarmResponseVM>> search(@RequestBody SearchFarmDTO searchFarmDTO){
        List<Farm> farmList= farmService.search(searchFarmDTO);
        List<FarmResponseVM> farmResponseVMS = farmList.stream()
                .map(farmVMMapper::toFarmResponseVM)
                .toList();
        return ResponseEntity.ok().body(farmResponseVMS);
    }
}
