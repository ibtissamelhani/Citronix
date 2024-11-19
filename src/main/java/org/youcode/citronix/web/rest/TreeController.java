package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.DTO.Tree.TreeRequestDTO;
import org.youcode.citronix.domain.entities.Tree;
import org.youcode.citronix.service.TreeService;

import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<Tree>> getTreesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Tree> treePage = treeService.getAllTreesPaginated(page,size);
        return ResponseEntity.ok(treePage);
    }

    @GetMapping("/{fieldId}")
    public ResponseEntity<Page<Tree>> getTreesByFieldId(@PathVariable UUID fieldId,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Page<Tree> treePage = treeService.getAllTreesByFieldId(fieldId,page,size);
        return ResponseEntity.ok(treePage);
    }

    @DeleteMapping("/delete/{treeId}")
    public ResponseEntity<String> deleteTree(@PathVariable UUID treeId) {
        treeService.delete(treeId);
        return ResponseEntity.ok("Tree deleted successfully");
    }
}
