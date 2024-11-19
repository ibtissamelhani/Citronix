package org.youcode.citronix.web.VM.Field;

import lombok.*;
import org.youcode.citronix.domain.entities.Field;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldVM {
    private String farmName;
    private double area;
    private String farmLocation;

    public static FieldVM fromEntity(Field field) {
        FieldVM vm = new FieldVM();
        vm.setFarmName(field.getFarm().getName());
        vm.setArea(field.getArea());
        vm.setFarmLocation(field.getFarm().getLocation());
        return vm;
    }
}
