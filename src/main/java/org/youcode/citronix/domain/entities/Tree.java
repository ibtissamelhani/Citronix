package org.youcode.citronix.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate plantingDate;

    @ManyToOne
    private Field field;

    @OneToMany(mappedBy = "tree")
    private List<HarvestDetail> harvestDetails;

    public int getAge() {
        LocalDate today = LocalDate.now();
        Period age = Period.between(plantingDate, today);
        return age.getYears();
    }

    public double getProductivity() {
        int age = getAge();
        double productivity = 0.0;
        if (age < 3) {
            productivity = 2.5;
        } else if (age <= 10) {
            productivity= 12 ;
        } else if (age <= 20){
            productivity = 20 ;
        }else {
            return 0;
        }

        if (plantingDate.getMonth().equals(Month.MARCH)){
            productivity = productivity + (productivity * 20/100);
        }
        
        return productivity;
    }
}
