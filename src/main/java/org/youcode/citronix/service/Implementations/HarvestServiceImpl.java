package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.domain.entities.HarvestDetail;
import org.youcode.citronix.domain.entities.Tree;
import org.youcode.citronix.domain.enums.Season;
import org.youcode.citronix.repository.HarvestDetailRepository;
import org.youcode.citronix.repository.HarvestRepository;
import org.youcode.citronix.service.FieldService;
import org.youcode.citronix.service.HarvestService;
import org.youcode.citronix.util.SeasonUtils;
import org.youcode.citronix.web.exception.Harvest.HarvestAlreadyExistException;
import org.youcode.citronix.web.exception.Harvest.HarvestNotFoundException;
import org.youcode.citronix.web.exception.InvalidCredentialsException;
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
        Season harvestSeason = SeasonUtils.getSeasonFromDate(harvest.getDate());

        List<Tree> trees = fieldToHarvest.getTrees().stream()
                .filter(tree -> !harvestDetailRepository.existsByTreeAndHarvestSeason(tree, harvestSeason))
                .toList();

        if (trees.isEmpty()){
            throw new TreeNotFoundException("All trees has been harvested this season.");
        }

        double totalQuantity = trees.stream()
                .mapToDouble(Tree::getProductivity)
                .sum();

        Harvest harvestToSave = Harvest.builder()
                .date(harvest.getDate())
                .season(harvestSeason)
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

    @Override
    public Harvest findById(UUID id) {
        if (id == null){
            throw new InvalidCredentialsException("harvest ID is Required");
        }
        return harvestRepository.findById(id)
                .orElseThrow(()-> new HarvestNotFoundException("harvest Not Found"));
    }

    @Override
    public void delete(UUID id) {
        Harvest harvestToDelete = findById(id);
        harvestRepository.delete(harvestToDelete);
    }

    @Override
    public List<Harvest> getHarvestsBySeason(Season season) {
        return harvestRepository.findBySeason(season);
    }
}
