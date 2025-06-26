package com.hechoconamor.hcaapi._product.p_material.services.impl;

import com.hechoconamor.hcaapi._product.p_material.dtos.ProductMaterialRequestDTO;
import com.hechoconamor.hcaapi._product.p_material.dtos.ProductMaterialResponseDTO;
import com.hechoconamor.hcaapi._product.p_material.entity.ProductMaterial;
import com.hechoconamor.hcaapi._product.p_material.repository.ProductMaterialRepository;
import com.hechoconamor.hcaapi._product.p_material.services.ProductMaterialService;
import com.hechoconamor.hcaapi._product.p_material.validator.ProductMaterialValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductMaterialServiceImpl implements ProductMaterialService {

    private final ProductMaterialRepository productMaterialRepository;
    private final ProductMaterialValidator productMaterialValidator;
    private final ModelMapper modelMapper;

    public ProductMaterialServiceImpl(ProductMaterialRepository productMaterialRepository,
                                      ProductMaterialValidator productMaterialValidator,
                                      ModelMapper modelMapper) {
        this.productMaterialRepository = productMaterialRepository;
        this.productMaterialValidator = productMaterialValidator;
        this.modelMapper = modelMapper;
    }

    // ********** Registrar un nuevo material en el sistema ********** //
    @Override
    public ProductMaterialResponseDTO registerMaterial(ProductMaterialRequestDTO productMaterialRequestDTO) {
        // Validar antes de registrar
        productMaterialValidator.validateBeforeRegister(productMaterialRequestDTO);

        // Convertir DTO a entidad
        ProductMaterial newProductMaterial = modelMapper.map(productMaterialRequestDTO, ProductMaterial.class);
        newProductMaterial.setId(null);

        // Guardar en la base de datos
        ProductMaterial savedProductMaterial = productMaterialRepository.save(newProductMaterial);

        // Convertir entidad a DTO para retornarlo
        return modelMapper.map(savedProductMaterial, ProductMaterialResponseDTO.class);
    }

    // ********** Obtener todos los materiales registrados ********** //
    @Override
    public List<ProductMaterialResponseDTO> findAll() {
        return productMaterialRepository.findAll()
                .stream()
                .map(productMaterial -> modelMapper.map(productMaterial, ProductMaterialResponseDTO.class))
                .collect(Collectors.toList());
    }

    // ********** Buscar material por ID ********** //
    @Override
    public ProductMaterialResponseDTO findById(Integer id) {
        ProductMaterial productMaterial = productMaterialRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Material con ID: " + id + " no encontrado"));
        return modelMapper.map(productMaterial, ProductMaterialResponseDTO.class);
    }

    // ********** Buscar material por nombre ********** //
    @Override
    public ProductMaterialResponseDTO findByNameIgnoreCase(String name) {
        ProductMaterial productMaterial = productMaterialRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Material con nombre " + name + " no encontrado"));
        return modelMapper.map(productMaterial, ProductMaterialResponseDTO.class);
    }

    // ********** Actualizar el nombre de un color existente ********** //
    @Override
    public ProductMaterialResponseDTO updateMaterial(Integer id, ProductMaterialRequestDTO productMaterialRequestDTO) {
        // Validamos antes de actualizar
        productMaterialValidator.validateBeforeUpdate(id, productMaterialRequestDTO);

        // Buscar el material por su ID
        ProductMaterial existingMaterial = productMaterialRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Material con ID: " + id + " no encontado"));

        // Actualizar los datos de la entidad con los valores del DTO
        existingMaterial.setName(productMaterialRequestDTO.getName());

        // Guardar en la base de datos
        ProductMaterial updatedMaterial = productMaterialRepository.save(existingMaterial);

        // Convertir entidad a DTO para retornarlo
        return modelMapper.map(updatedMaterial, ProductMaterialResponseDTO.class);
    }

    // ********** Eliminar un material existente ********** //
    @Override
    public void deleteMaterial(Integer id) {
        // Buscar el material por su ID
        ProductMaterial existingMaterial = productMaterialRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Material con ID: " + id + " no encontrado"));

        // Eliminar material de la base de datos
        productMaterialRepository.delete(existingMaterial);
    }
}
