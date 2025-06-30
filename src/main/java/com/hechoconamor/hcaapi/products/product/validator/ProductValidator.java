package com.hechoconamor.hcaapi.products.product.validator;

import com.hechoconamor.hcaapi.products.p_category.repository.ProductCategoryRepository;
import com.hechoconamor.hcaapi.products.p_color.repository.ProductColorRepository;
import com.hechoconamor.hcaapi.products.p_material.repository.ProductMaterialRepository;
import com.hechoconamor.hcaapi.products.p_size.repository.ProductSizeRepository;
import com.hechoconamor.hcaapi.products.p_status.repository.ProductStatusRepository;
import com.hechoconamor.hcaapi.products.product.dtos.ProductRequestDTO;
import com.hechoconamor.hcaapi.products.product.repository.ProductRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductValidator {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;
    private final ProductColorRepository colorRepository;
    private final ProductMaterialRepository materialRepository;
    private final ProductSizeRepository sizeRepository;
    private final ProductStatusRepository statusRepository;

    public void validateBeforeRegister(ProductRequestDTO requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del producto no puede estar vacío.");
        }

        if (requestDTO.getDescription() == null || requestDTO.getDescription().isBlank()) {
            throw new BadRequestException("La descripción del producto no puede estar vacía.");
        }

        if (requestDTO.getPrice() == null || requestDTO.getPrice().signum() < 0) {
            throw new BadRequestException("El precio del producto debe ser mayor o igual a 0.");
        }

        if (!categoryRepository.existsById(requestDTO.getCategoryId())) {
            throw new BadRequestException("La categoría especificada no existe.");
        }

        if (!colorRepository.existsById(requestDTO.getColorId())) {
            throw new BadRequestException("El color especificado no existe.");
        }

        if (!materialRepository.existsById(requestDTO.getMaterialId())) {
            throw new BadRequestException("El material especificado no existe.");
        }

        if (!sizeRepository.existsById(requestDTO.getSizeId())) {
            throw new BadRequestException("El tamaño especificado no existe.");
        }

        if (!statusRepository.existsById(requestDTO.getStatusId())) {
            throw new BadRequestException("El estado especificado no existe.");
        }

        if (productRepository.findByNameIgnoreCase(requestDTO.getName()).isPresent()) {
            throw new ConflictException("Ya existe un producto con el nombre: " + requestDTO.getName());
        }
    }

    public void validateBeforeUpdate(Integer id, ProductRequestDTO requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del producto no puede estar vacío.");
        }

        if (requestDTO.getDescription() == null || requestDTO.getDescription().isBlank()) {
            throw new BadRequestException("La descripción del producto no puede estar vacía.");
        }

        if (requestDTO.getPrice() == null || requestDTO.getPrice().signum() < 0) {
            throw new BadRequestException("El precio del producto debe ser mayor o igual a 0.");
        }

        if (!categoryRepository.existsById(requestDTO.getCategoryId())) {
            throw new BadRequestException("La categoría especificada no existe.");
        }

        if (!colorRepository.existsById(requestDTO.getColorId())) {
            throw new BadRequestException("El color especificado no existe.");
        }

        if (!materialRepository.existsById(requestDTO.getMaterialId())) {
            throw new BadRequestException("El material especificado no existe.");
        }

        if (!sizeRepository.existsById(requestDTO.getSizeId())) {
            throw new BadRequestException("El tamaño especificado no existe.");
        }

        if (!statusRepository.existsById(requestDTO.getStatusId())) {
            throw new BadRequestException("El estado especificado no existe.");
        }

        productRepository.findByNameIgnoreCase(requestDTO.getName())
                .ifPresent(existingProduct -> {
                    if (!existingProduct.getId().equals(id)) {
                        throw new ConflictException("El nombre del producto ya está en uso por otro registro.");
                    }
                });
    }
}
