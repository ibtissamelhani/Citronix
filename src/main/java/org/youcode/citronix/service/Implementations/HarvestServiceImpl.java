package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.domain.entities.HarvestDetail;
import org.youcode.citronix.domain.entities.Tree;
import org.youcode.citronix.repository.HarvestDetailRepository;
import org.youcode.citronix.repository.HarvestRepository;
import org.youcode.citronix.service.FieldService;
import org.youcode.citronix.service.HarvestService;
import org.youcode.citronix.web.exception.Harvest.HarvestAlreadyExistException;
import org.youcode.citronix.web.exception.Tree.TreeNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final FieldService fieldService;
    private final HarvestDetailRepository harvestDetailRepository;

    @Transactional
    @Override
    public Harvest createHarvest(UUID fieldId, Harvest harvest) {

        Field fieldToHarvest = fieldService.getFieldById(fieldId);
        boolean exist = harvestRepository.existsByFieldAndSeason(fieldToHarvest,harvest.getSeason());
        if (exist){
            throw new HarvestAlreadyExistException("A harvest already exists for this field and season.");
        }

        List<Tree> trees = fieldToHarvest.getTrees().stream()
                .filter(tree -> !harvestDetailRepository.existsByTreeAndHarvestSeason(tree, harvest.getSeason()))
                .toList();

        if (trees.isEmpty()){
            throw new TreeNotFoundException("All trees has been harvested this season.");
        }

        double totalQuantity = trees.stream()
                .mapToDouble(Tree::getProductivity)
                .sum();

        Harvest harvestToSave = Harvest.builder()
                .field(fieldToHarvest)
                .date(harvest.getDate())
                .season(harvest.getSeason())
                .totalQuantity(totalQuantity)
                .build();

        Harvest savedHarvest = harvestRepository.save(harvestToSave);

        List<HarvestDetail> harvestDetails = trees.stream()
                .map(tree -> HarvestDetail.builder()
                        .harvest(savedHarvest)
                        .tree(tree)
                        .quantity(tree.getProductivity())
                        .build())
                .toList();
        harvestDetailRepository.saveAll(harvestDetails);
        return savedHarvest;
    }
}
