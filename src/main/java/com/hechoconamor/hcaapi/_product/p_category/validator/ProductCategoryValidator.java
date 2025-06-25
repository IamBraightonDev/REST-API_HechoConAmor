package com.hechoconamor.hcaapi._product.p_category.validator;

import com.hechoconamor.hcaapi._product.p_category.dtos.ProductCategoryRequestDTO;
import com.hechoconamor.hcaapi._product.p_category.repository.ProductCategoryRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryValidator {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryValidator(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public void validateBeforeRegister(ProductCategoryRequestDTO productCategoryRequestDTO) {
        // Validar nombre
        if(productCategoryRequestDTO.getName() == null || productCategoryRequestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre de la categoría no puede estar vacío.");
        }

        // Verifica si existe otra categoría con el mismo nombre
        if(productCategoryRepository.findByNameIgnoreCase(productCategoryRequestDTO.getName()).isPresent()) {
            throw new ConflictException("El nombre de la categoría ya existe.");
        }
    }

    public void validateBeforeUpdate(Integer id, ProductCategoryRequestDTO productCategoryRequestDTO) {
        // Validar nombre
        if(productCategoryRequestDTO.getName() == null || productCategoryRequestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre de la categoría no puede estar vacío");
        }

        // Verifica si existe otra categoría con el mismo nombre
        productCategoryRepository.findByNameIgnoreCase(productCategoryRequestDTO.getName())
                .ifPresent(existingCategory -> {
                    if(!existingCategory.getId().equals(id)) {
                        throw new ConflictException("El nombre de la categoría ya existe");
                    }
                });
    }
}
