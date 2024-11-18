package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
}
