package org.youcode.citronix.web.VM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.citronix.domain.entities.Tree;
import org.youcode.citronix.web.VM.Tree.TreeCreationVm;
import org.youcode.citronix.web.VM.Tree.TreeResponseVM;

@Mapper(componentModel = "spring")
public interface TreeVMMapper {
    @Mapping(target = "field.id", source = "fieldId")
    Tree toTree(TreeCreationVm treeCreationVm);

    @Mapping(target = "age", expression = "java(tree.getAge())")
    @Mapping(target = "productivity", expression = "java(tree.getProductivity())")
    TreeResponseVM toResponseVM(Tree tree);
}
