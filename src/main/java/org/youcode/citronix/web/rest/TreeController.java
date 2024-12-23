package org.youcode.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.DTO.TreeDetailsDTO;
import org.youcode.citronix.domain.entities.Tree;
import org.youcode.citronix.service.TreeService;
import org.youcode.citronix.web.VM.Tree.TreeCreationVm;
import org.youcode.citronix.web.VM.Tree.TreeResponseVM;
import org.youcode.citronix.web.VM.mapper.TreeVMMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/trees")
@AllArgsConstructor
public class TreeController {
    private final TreeService treeService;
    private final TreeVMMapper treeVMMapper;

    @PostMapping("/save")
    public ResponseEntity<TreeResponseVM> saveTree(@RequestBody @Valid TreeCreationVm treeCreationVm) {
        Tree tree = treeVMMapper.toTree(treeCreationVm);
        Tree savedTree = treeService.saveTree(tree);
        TreeResponseVM treeResponseVM = treeVMMapper.toResponseVM(savedTree);
        return ResponseEntity.status(HttpStatus.CREATED).body(treeResponseVM);
    }

    @GetMapping
    public ResponseEntity<Page<TreeResponseVM>> getTreesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Tree> treePage = treeService.getAllTreesPaginated(page,size);
        List<TreeResponseVM> treeResponseVMS = treePage.stream().map(treeVMMapper::toResponseVM).toList();
        Page<TreeResponseVM> treeResponseVMPage = new PageImpl<>(treeResponseVMS, treePage.getPageable(), treePage.getTotalElements());

        return ResponseEntity.ok(treeResponseVMPage);
    }

    @GetMapping("/{fieldId}")
    public ResponseEntity<Page<TreeResponseVM>> getTreesByFieldId(@PathVariable UUID fieldId,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Page<Tree> treePage = treeService.getAllTreesByFieldId(fieldId,page,size);
        List<TreeResponseVM> treeResponseVMS = treePage.stream().map(treeVMMapper::toResponseVM).toList();
        Page<TreeResponseVM> treeResponseVMPage = new PageImpl<>(treeResponseVMS, treePage.getPageable(), treePage.getTotalElements());
        return ResponseEntity.ok(treeResponseVMPage);
    }

    @DeleteMapping("/delete/{treeId}")
    public ResponseEntity<String> deleteTree(@PathVariable UUID treeId) {
        treeService.delete(treeId);
        return ResponseEntity.ok("Tree deleted successfully");
    }

    @GetMapping("/{treeId}/details")
    public ResponseEntity<TreeDetailsDTO> getTreeDetails(@PathVariable UUID treeId) {
        TreeDetailsDTO treeDetails = treeService.getTreeDetails(treeId);
        return ResponseEntity.ok(treeDetails);
    }
}
