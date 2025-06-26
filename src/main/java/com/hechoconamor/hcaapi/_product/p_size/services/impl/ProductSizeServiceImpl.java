package com.hechoconamor.hcaapi._product.p_size.services.impl;

import com.hechoconamor.hcaapi._product.p_size.dtos.ProductSizeRequestDTO;
import com.hechoconamor.hcaapi._product.p_size.dtos.ProductSizeResponseDTO;
import com.hechoconamor.hcaapi._product.p_size.entity.ProductSize;
import com.hechoconamor.hcaapi._product.p_size.repository.ProductSizeRepository;
import com.hechoconamor.hcaapi._product.p_size.services.ProductSizeService;
import com.hechoconamor.hcaapi._product.p_size.validator.ProductSizeValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    private final ProductSizeRepository productSizeRepository;
    private final ProductSizeValidator productSizeValidator;
    private final ModelMapper modelMapper;

    public ProductSizeServiceImpl(ProductSizeRepository productSizeRepository,
                                  ProductSizeValidator productSizeValidator,
                                  ModelMapper modelMapper) {
        this.productSizeRepository = productSizeRepository;
        this.productSizeValidator = productSizeValidator;
        this.modelMapper = modelMapper;
    }

    // ********** Registrar un nuevo tamaño en el sistema ********** //
    @Override
    public ProductSizeResponseDTO registerSize(ProductSizeRequestDTO productSizeRequestDTO) {
        // Validar datos antes de registrar
        productSizeValidator.validateBeforeRegister(productSizeRequestDTO);

        // Convertir DTO a entidad
        ProductSize newProductSize = modelMapper.map(productSizeRequestDTO, ProductSize.class);
        newProductSize.setId(null);

        // Guardar datos de la entidad en la base de datos
        ProductSize savedProductSize = productSizeRepository.save(newProductSize);

        // Convertir entidad a DTO para retornarla
        return modelMapper.map(savedProductSize, ProductSizeResponseDTO.class);
    }

    // ********** Obtener todos los tamaños registrados ********** //
    @Override
    public List<ProductSizeResponseDTO> findAll() {
        return productSizeRepository.findAll()
                .stream()
                .map(productSize -> modelMapper.map(productSize, ProductSizeResponseDTO.class))
                .collect(Collectors.toList());
    }

    // ********** Buscar tamaño por ID ********** //
    @Override
    public ProductSizeResponseDTO findById(Integer id) {
        ProductSize productSize = productSizeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tamaño con ID: " + id + " no encontrado"));
        return modelMapper.map(productSize, ProductSizeResponseDTO.class);
    }

    // ********** Buscar tamaño por nombre ********** //
    @Override
    public ProductSizeResponseDTO findByNameIgnoreCase(String name) {
        ProductSize productSize = productSizeRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Tamaño con nombre " + name + " no encontrado"));
        return modelMapper.map(productSize, ProductSizeResponseDTO.class);
    }

    // ********** Actualizar el nombre de un color existente ********** //
    @Override
    public ProductSizeResponseDTO updateSize(Integer id, ProductSizeRequestDTO productSizeRequestDTO) {
        // Validar los datos antes de actualizar
        productSizeValidator.validateBeforeUpdate(id, productSizeRequestDTO);

        // Buscar el tamaño por su ID
        ProductSize existingSize = productSizeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tamaño con ID: " + " no encontrado"));

        // Actualizar datos de la entidad con los valores del DTO
        existingSize.setName(productSizeRequestDTO.getName());

        // Guardar entidad actualizada en la base de datos
        ProductSize updatedSize = productSizeRepository.save(existingSize);

        // Convertir entidad a DTO para retornarlo
        return modelMapper.map(updatedSize, ProductSizeResponseDTO.class);
    }
    // ********** Eliminar un material existente ********** //
    @Override
    public void deleteSize(Integer id) {
        // Buscar tamaño por su ID
        ProductSize existingSize = productSizeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tamaño con ID: " + id + " no encotrado"));

        // Eliminar entidad de la base de datos
        productSizeRepository.delete(existingSize);
    }
}
