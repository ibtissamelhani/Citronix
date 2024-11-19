package org.youcode.citronix.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.youcode.citronix.domain.enums.Season;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Season season;
    private LocalDate date;
    private double totalQuantity;

    @OneToMany(mappedBy = "harvest")
    private List<HarvestDetail> harvestDetails;

    @ManyToOne
    private Field field;

}
