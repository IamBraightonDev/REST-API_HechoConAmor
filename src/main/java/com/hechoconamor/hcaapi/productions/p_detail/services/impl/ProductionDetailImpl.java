package com.hechoconamor.hcaapi.productions.p_detail.services.impl;

import com.hechoconamor.hcaapi.productions.p_detail.dtos.ProductionDetailResponseDTO;
import com.hechoconamor.hcaapi.productions.p_detail.entity.ProductionDetail;
import com.hechoconamor.hcaapi.productions.p_detail.mapper.ProductionDetailMapper;
import com.hechoconamor.hcaapi.productions.p_detail.repository.ProductionDetailRepository;
import com.hechoconamor.hcaapi.productions.p_detail.services.ProductionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductionDetailImpl implements ProductionDetailService {

    private final ProductionDetailRepository productionDetailRepository;
    private final ProductionDetailMapper productionDetailMapper;

    @Override
    public List<ProductionDetailResponseDTO> findByProductionId(Integer productionId) {
        List<ProductionDetail> details = productionDetailRepository.findByProductionId(productionId);
        return details.stream()
                .map(productionDetailMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductionDetailResponseDTO findById(Integer id) {
        ProductionDetail detail = productionDetailRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el detalle con ID " + id));
        return productionDetailMapper.toResponseDTO(detail);
    }

}
