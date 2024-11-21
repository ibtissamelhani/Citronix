package org.youcode.citronix.service;

import org.springframework.data.domain.Page;
import org.youcode.citronix.DTO.TreeDetailsDTO;
import org.youcode.citronix.domain.entities.Tree;

import java.util.UUID;

public interface TreeService {
    Tree saveTree(Tree tree);
    Page<Tree> getAllTreesPaginated(int page, int size);
    Page<Tree> getAllTreesByFieldId(UUID fieldId,int page, int size);
    void delete(UUID id);
    Tree findById(UUID id);
    TreeDetailsDTO getTreeDetails(UUID id);
}
