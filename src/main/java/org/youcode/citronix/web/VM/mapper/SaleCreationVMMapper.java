package org.youcode.citronix.web.VM.mapper;

import org.mapstruct.Mapper;
import org.youcode.citronix.domain.entities.Sale;
import org.youcode.citronix.web.VM.Sale.SaleCreationVm;

@Mapper(componentModel = "spring")
public interface SaleCreationVMMapper {

    Sale toSale(SaleCreationVm saleCreationVm);
    SaleCreationVm toSaleCreationVm(Sale sale);
}
