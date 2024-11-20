package org.youcode.citronix.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.youcode.citronix.domain.enums.Season;
import org.youcode.citronix.web.exception.InvalidCredentialsException;

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

    public void reduceTotalQuantity(double saleQuantity) {
        if (saleQuantity <= 0) {
            throw new InvalidCredentialsException("Sale quantity must be positive.");
        }
        if (saleQuantity > this.totalQuantity) {
            throw new IllegalArgumentException("Sale quantity exceeds available quantity of harvest.");
        }

        this.totalQuantity -= saleQuantity;
    }

}
