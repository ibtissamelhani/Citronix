package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.DTO.Field.FieldRequestDTO;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.service.FieldService;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/fields")
@AllArgsConstructor
public class FieldController {

    private final FieldService fieldService;

    @PostMapping("/save")
    public ResponseEntity<Field> addField(@Valid @RequestBody FieldRequestDTO fieldRequestDTO) {
        Field createdField = fieldService.addField(fieldRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdField);
    }

    @GetMapping("/details/{fieldId}")
    public ResponseEntity<Field> getFieldById(@PathVariable UUID fieldId) {
        Field field = fieldService.getFieldById(fieldId);
        return ResponseEntity.ok(field);
    }

    @DeleteMapping("/delete/{fieldId}")
    public ResponseEntity<String> deleteField(@PathVariable UUID fieldId) {
        fieldService.deleteField(fieldId);
        return ResponseEntity.ok("Field deleted successfully");
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<Page<Field>> findAllByFarmId(@PathVariable UUID farmId,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size ) {
        Page<Field> fields = fieldService.findAllByFarmId(farmId, page,size);
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Field>> getFarmsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Field> fieldPage = fieldService.getFieldsWithPagination(page,size);
        return ResponseEntity.ok(fieldPage);
    }

    @PutMapping("/update/{fieldId}")
    public ResponseEntity<Field> updateField(@PathVariable UUID fieldId, @RequestBody double newArea) {
        Field createdField = fieldService.updateField(fieldId,newArea);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdField);
    }
}
