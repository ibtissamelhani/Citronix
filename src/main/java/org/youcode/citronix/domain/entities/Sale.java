package org.youcode.citronix.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate date;
    private double unitPrice;
    private double quantity;
    private String client;

    @ManyToOne
    private Harvest harvest;

    public double calculateRevenue() {
        return unitPrice * quantity;
    }
}
