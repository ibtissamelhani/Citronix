package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.service.FieldService;
import org.youcode.citronix.web.VM.Field.FieldCreationVM;
import org.youcode.citronix.web.VM.Field.FieldResponseVM;
import org.youcode.citronix.web.VM.mapper.FieldVMMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/fields")
@AllArgsConstructor
public class FieldController {

    private final FieldService fieldService;
    private final FieldVMMapper fieldVMMapper;

    @PostMapping("/save")
    public ResponseEntity<FieldResponseVM> addField(@Valid @RequestBody FieldCreationVM fieldCreationVM) {
        Field fieldToSave = fieldVMMapper.toField(fieldCreationVM);

        Field createdField = fieldService.addField(fieldToSave);

        FieldResponseVM fieldResponseVM = fieldVMMapper.toFieldResponseVM(createdField);
        return ResponseEntity.status(HttpStatus.CREATED).body(fieldResponseVM);
    }

    @GetMapping("/details/{fieldId}")
    public ResponseEntity<FieldResponseVM> getFieldById(@PathVariable UUID fieldId) {
        Field field = fieldService.getFieldById(fieldId);
        return ResponseEntity.ok(fieldVMMapper.toFieldResponseVM(field));
    }

    @DeleteMapping("/delete/{fieldId}")
    public ResponseEntity<String> deleteField(@PathVariable UUID fieldId) {
        fieldService.deleteField(fieldId);
        return ResponseEntity.ok("Field deleted successfully");
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<Page<FieldResponseVM>> findAllByFarmId(@PathVariable UUID farmId,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size ) {
        Page<Field> fields = fieldService.findAllByFarmId(farmId, page,size);
        List<FieldResponseVM> fieldResponseVMS = fields.stream().map(fieldVMMapper::toFieldResponseVM).toList();
        Page<FieldResponseVM>   fieldResponseVMPage = new PageImpl<>(fieldResponseVMS, fields.getPageable(), fields.getTotalElements());

        return new ResponseEntity<>(fieldResponseVMPage, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<FieldResponseVM>> getFarmsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Field> fieldPage = fieldService.getFieldsWithPagination(page,size);
        List<FieldResponseVM> fieldResponseVMS = fieldPage.stream().map(fieldVMMapper::toFieldResponseVM).toList();
        Page<FieldResponseVM>   fieldResponseVMPage = new PageImpl<>(fieldResponseVMS, fieldPage.getPageable(), fieldPage.getTotalElements());

        return ResponseEntity.ok(fieldResponseVMPage);
    }

    @PutMapping("/update/{fieldId}")
    public ResponseEntity<FieldResponseVM> updateField(@PathVariable UUID fieldId, @RequestBody double newArea) {
        Field createdField = fieldService.updateField(fieldId,newArea);
        return ResponseEntity.status(HttpStatus.CREATED).body(fieldVMMapper.toFieldResponseVM(createdField));
    }
}
