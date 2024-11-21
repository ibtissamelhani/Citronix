package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.citronix.DTO.TreeDetailsDTO;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.domain.entities.Tree;
import org.youcode.citronix.repository.TreeRepository;
import org.youcode.citronix.service.FieldService;
import org.youcode.citronix.service.TreeService;
import org.youcode.citronix.web.exception.InvalidCredentialsException;
import org.youcode.citronix.web.exception.Tree.InvalidPlantingDateException;
import org.youcode.citronix.web.exception.Tree.TreeDensityException;
import org.youcode.citronix.web.exception.Tree.TreeNotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private final FieldService fieldService;

    @Override
    public Tree saveTree(Tree tree) {

        validatePlantingDate(tree.getPlantingDate());
        Field field = fieldService.getFieldById(tree.getField().getId());

        validateTreeDensity(field);

        tree.setField(field);
        return treeRepository.save(tree);
    }

    private void validatePlantingDate(LocalDate plantingDate) {
        Month month = plantingDate.getMonth();
        if (month != Month.MARCH && month != Month.APRIL && month != Month.MAY) {
            throw new InvalidPlantingDateException("Trees can only be planted between March and May.");
        }
    }

    private void validateTreeDensity(Field field) {
        long treeCount = treeRepository.countByFieldId(field.getId());
        double maxTrees = field.getArea() * 100;
        if (treeCount >= maxTrees) {
            throw new TreeDensityException("Field cannot contain more than 100 trees per hectare.");
        }
    }

    @Override
    public Page<Tree> getAllTreesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return treeRepository.findAll(pageable);
    }

    @Override
    public Page<Tree> getAllTreesByFieldId(UUID fieldId, int page, int size) {
        if (fieldId == null){
            throw new InvalidCredentialsException("field Id is required");
        }
        Pageable pageable = PageRequest.of(page,size);
        return treeRepository.findAllByFieldId(fieldId,pageable);
    }

    @Override
    public Tree findById(UUID id) {
        if (id == null){
            throw new InvalidCredentialsException("id is required");
        }
        return treeRepository.findById(id)
                .orElseThrow(()-> new TreeNotFoundException("tree not found"));
    }

    @Override
    public void delete(UUID id) {
        Tree treeToDelete = findById(id);
        treeRepository.delete(treeToDelete);
    }

    @Override
    public TreeDetailsDTO getTreeDetails(UUID id) {
        Tree tree = findById(id);
        return TreeDetailsDTO.builder()
                .plantingDate(tree.getPlantingDate())
                .age(tree.getAge())
                .productivity(tree.getProductivity())
                .farmName(tree.getField().getFarm().getName())
                .farmLocation(tree.getField().getFarm().getLocation())
                .fieldId(tree.getField().getId())
                .build();
    }
}
