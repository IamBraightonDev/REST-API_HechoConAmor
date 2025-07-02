package com.hechoconamor.hcaapi.productions.production.validator;

import com.hechoconamor.hcaapi.productions.p_detail.dtos.ProductionDetailRequestDTO;
import com.hechoconamor.hcaapi.productions.p_detail.validator.ProductionDetailValidator;
import com.hechoconamor.hcaapi.productions.production.dtos.ProductionRequestDTO;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.supplies.supply.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductionValidator {

    private final SupplyRepository supplyRepository;
    private final ProductionDetailValidator productionDetailValidator;

    public void validateBeforeRegister(ProductionRequestDTO requestDTO) {
        if (requestDTO.getDetails() == null || requestDTO.getDetails().isEmpty()) {
            throw new BadRequestException("Debe incluir al menos un detalle de producción.");
        }

        for (ProductionDetailRequestDTO detailDTO : requestDTO.getDetails()) {
            productionDetailValidator.validateBeforeRegister(detailDTO);
        }
    }
}
