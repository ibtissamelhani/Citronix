package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.DTO.Field.FieldRequestDTO;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.service.FieldService;

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
}
