package org.youcode.citronix.service;

import org.youcode.citronix.DTO.Tree.TreeRequestDTO;
import org.youcode.citronix.domain.entities.Tree;

public interface TreeService {
    Tree saveTree(TreeRequestDTO treeRequestDTO);
}
