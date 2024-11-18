package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.citronix.domain.entities.Tree;
import org.youcode.citronix.repository.TreeRepository;
import org.youcode.citronix.service.TreeService;
import org.youcode.citronix.web.exception.Tree.InvalidPlantingDateException;

import java.time.LocalDate;
import java.time.Month;

@Service
@AllArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;

    @Override
    public Tree saveTree(Tree tree) {
        validatePlantingDate(tree.getPlantingDate());
        return treeRepository.save(tree);
    }

    private void validatePlantingDate(LocalDate plantingDate) {
        Month month = plantingDate.getMonth();
        if (month != Month.MARCH && month != Month.APRIL && month != Month.MAY) {
            throw new InvalidPlantingDateException("Trees can only be planted between March and May.");
        }
    }
}
