package com.hechoconamor.hcaapi.productions.production.mapper;

import com.hechoconamor.hcaapi.productions.p_detail.dtos.ProductionDetailResponseDTO;
import com.hechoconamor.hcaapi.productions.p_detail.entity.ProductionDetail;
import com.hechoconamor.hcaapi.productions.production.dtos.ProductionRequestDTO;
import com.hechoconamor.hcaapi.productions.production.dtos.ProductionResponseDTO;
import com.hechoconamor.hcaapi.productions.production.entity.Production;
import com.hechoconamor.hcaapi.products.product.repository.ProductRepository;
import com.hechoconamor.hcaapi.supplies.supply.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductionMapper {

    private final SupplyRepository supplyRepository;
    private final ProductRepository productRepository;

    public Production toEntity(ProductionRequestDTO dto) {
        Production production = new Production();
        production.setProductionDate(LocalDateTime.now());
        production.setObservation(dto.getObservation());

        List<ProductionDetail> details = dto.getDetails().stream().map(detailDto -> {
            ProductionDetail detail = new ProductionDetail();
            detail.setSupply(
                    supplyRepository.findById(detailDto.getSupplyId())
                            .orElseThrow(() -> new NoSuchElementException("Supply no encontrado")));

            if (detailDto.getProductId() != null) {
                detail.setProduct(
                        productRepository.findById(detailDto.getProductId())
                                .orElseThrow(() -> new NoSuchElementException("Product no encontrado")));
            }

            detail.setQuantityUsed(detailDto.getQuantityUsed());
            detail.setQuantityProduced(detailDto.getQuantityProduced());
            detail.setProduction(production); // Importante
            return detail;
        }).collect(Collectors.toList());

        production.setDetails(details);
        return production;
    }

    public ProductionResponseDTO toResponseDTO(Production production) {
        ProductionResponseDTO dto = new ProductionResponseDTO();
        dto.setId(production.getId());
        dto.setProductionDate(production.getProductionDate());
        dto.setObservation(production.getObservation());

        dto.setDetails(production.getDetails().stream().map(detail -> {
            ProductionDetailResponseDTO d = new ProductionDetailResponseDTO();
            d.setId(detail.getId());

            // ✅ Añadir correctamente IDs además de los nombres
            d.setSupplyId(detail.getSupply().getId());
            d.setSupplyName(detail.getSupply().getName());

            if (detail.getProduct() != null) {
                d.setProductId(detail.getProduct().getId());
                d.setProductName(detail.getProduct().getName());
            }

            d.setQuantityUsed(detail.getQuantityUsed());
            d.setQuantityProduced(detail.getQuantityProduced());
            return d;
        }).collect(Collectors.toList()));

        return dto;
    }
}
