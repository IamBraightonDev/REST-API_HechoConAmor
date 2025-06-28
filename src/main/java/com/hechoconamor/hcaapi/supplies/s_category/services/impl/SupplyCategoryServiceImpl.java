package com.hechoconamor.hcaapi.supplies.s_category.services.impl;

import com.hechoconamor.hcaapi.supplies.s_category.dtos.SupplyCategoryRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_category.dtos.SupplyCategoryResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_category.entity.SupplyCategory;
import com.hechoconamor.hcaapi.supplies.s_category.mapper.SupplyCategoryMapper;
import com.hechoconamor.hcaapi.supplies.s_category.repository.SupplyCategoryRepository;
import com.hechoconamor.hcaapi.supplies.s_category.services.SupplyCategoryService;
import com.hechoconamor.hcaapi.supplies.s_category.validator.SupplyCategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SupplyCategoryServiceImpl implements SupplyCategoryService {

    private final SupplyCategoryRepository categoryRepository;
    private final SupplyCategoryValidator categoryValidator;
    private final SupplyCategoryMapper categoryMapper;

    // ********** Registrar una nueva categoria en el sistema ********** //
    @Override
    public SupplyCategoryResponseDTO registerCategory(SupplyCategoryRequestDTO requestDTO) {
        // Validar datos antes de actualizar
        categoryValidator.validateBeforeRegister(requestDTO);

        // Convertir el DTO a entidad
        SupplyCategory newCategory = categoryMapper.toEntity(requestDTO);

        // Guardar en la base de datos y convertir entidad a DTO para retornarlo
        return categoryMapper.toDTO(categoryRepository.save(newCategory));
    }

    // ********** Obtener todas las categorías registradas ********** //
    @Override
    public List<SupplyCategoryResponseDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .toList();
    }

    // ********** Buscar categoria por ID ********** //
    @Override
    public SupplyCategoryResponseDTO findById(Integer id) {
        SupplyCategory supplyCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoría con ID: " + id + " no encontrada"));

        return categoryMapper.toDTO(supplyCategory);
    }

    // ********** Buscar categoria por nombre ********** //
    @Override
    public SupplyCategoryResponseDTO findByName(String name) {
        SupplyCategory supplyCategory = categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Categoría con ID: " + name + " no encontrada"));

        return categoryMapper.toDTO(supplyCategory);
    }

    // ********** Actualizar nombre de una categoría existente ********** //
    @Override
    public SupplyCategoryResponseDTO updateCategory(Integer id, SupplyCategoryRequestDTO requestDTO) {
        // Validar datos antes de actualizar
        categoryValidator.validateBeforeUpdate(id, requestDTO);

        // Buscar la categoría existente por su ID
        SupplyCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoría con ID: " + id + " no encontrada"));

        // Actualizar los datos con los nuevos valores del DTO
        category.setName(requestDTO.getName());

        // Guardar cambios en la base de datos
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    // ********** Eliminar una categoría por su ID ********** //
    @Override
    public void deleteCategory(Integer id) {
        // Buscar la categoría por su ID
        SupplyCategory existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoría con ID: " + id + " no encontrada"));

        // Eliminar la categoría de la base de datos
        categoryRepository.delete(existingCategory);
    }
}
