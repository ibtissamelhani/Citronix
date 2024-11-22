package org.youcode.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.youcode.citronix.domain.entities.HarvestDetail;
import org.youcode.citronix.domain.entities.Tree;
import org.youcode.citronix.domain.enums.Season;

import java.util.UUID;

public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, UUID> {


    boolean existsByTreeAndHarvestSeason(Tree tree, Season season);

    @Query("SELECT CASE WHEN COUNT(hd) > 0 THEN TRUE ELSE FALSE END " +
            "FROM HarvestDetail hd " +
            "JOIN hd.harvest h " +
            "WHERE hd.tree = :tree AND h.season = :season AND YEAR(h.date) = :year")
    boolean existsByTreeAndSeasonAndYear(@Param("tree") Tree tree,
                                         @Param("season") Season season,
                                         @Param("year") int year);

}
