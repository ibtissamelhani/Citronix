package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.DTO.Tree.TreeRequestDTO;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.domain.entities.Tree;
import org.youcode.citronix.service.TreeService;
import org.youcode.citronix.web.VM.Farm.FarmRequestVM;

@RestController
@RequestMapping("v1/api/trees")
@AllArgsConstructor
public class TreeController {
    private final TreeService treeService;

    @PostMapping("/save")
    public ResponseEntity<Tree> saveTree(@RequestBody @Valid TreeRequestDTO treeRequestDTO) {
        Tree savedTree = treeService.saveTree(treeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTree);
    }

}
