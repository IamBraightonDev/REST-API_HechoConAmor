package com.hechoconamor.hcaapi._product.p_status.services.impl;

import com.hechoconamor.hcaapi._product.p_status.dtos.ProductStatusRequestDTO;
import com.hechoconamor.hcaapi._product.p_status.dtos.ProductStatusResponseDTO;
import com.hechoconamor.hcaapi._product.p_status.entity.ProductStatus;
import com.hechoconamor.hcaapi._product.p_status.repository.ProductStatusRepository;
import com.hechoconamor.hcaapi._product.p_status.services.ProductStatusService;
import com.hechoconamor.hcaapi._product.p_status.validator.ProductStatusValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductStatusServiceImpl implements ProductStatusService {

    private final ProductStatusRepository productStatusRepository;
    private final ProductStatusValidator productStatusValidator;
    private final ModelMapper modelMapper;

    public ProductStatusServiceImpl(ProductStatusRepository productStatusRepository, ProductStatusValidator productStatusValidator, ModelMapper modelMapper) {
        this.productStatusRepository = productStatusRepository;
        this.productStatusValidator = productStatusValidator;
        this.modelMapper = modelMapper;
    }

    // ********** Registrar un nuevo estado en el sistema ********** //
    @Override
    public ProductStatusResponseDTO registerStatus(ProductStatusRequestDTO productStatusRequestDTO) {
        // Validar los datos antes de registrar
        productStatusValidator.validateBeforeRegister(productStatusRequestDTO);

        // Convertir el DTO a entidad
        ProductStatus newProductStatus = modelMapper.map(productStatusRequestDTO, ProductStatus.class);
        newProductStatus.setId(null);

        // Guardar en la entidad los datos del DTO
        ProductStatus savedProductStatus = productStatusRepository.save(newProductStatus);

        // Convertir entidad a DTO para retornarlo
        return modelMapper.map(savedProductStatus, ProductStatusResponseDTO.class);
    }

    // ********** Obtener todos los estados registrados ********** //
    @Override
    public List<ProductStatusResponseDTO> findAll() {
        return productStatusRepository.findAll()
                .stream()
                .map(productStatus -> modelMapper.map(productStatus, ProductStatusResponseDTO.class))
                .collect(Collectors.toList());
    }

    // ********** Buscar estado por ID ********** //
    @Override
    public ProductStatusResponseDTO findById(Integer id) {
        ProductStatus productStatus = productStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estado con ID: " + id + " no encontrado"));
        return modelMapper.map(productStatus, ProductStatusResponseDTO.class);
    }

    // ********** Buscar estado por nombre ********** //
    @Override
    public ProductStatusResponseDTO findByNameIgnoreCase(String name) {
        ProductStatus productStatus = productStatusRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Estado con nombre " + name + " no encontrado"));
        return modelMapper.map(productStatus, ProductStatusResponseDTO.class);
    }

    // ********** Actualizar el nombre de un color existente ********** //
    @Override
    public ProductStatusResponseDTO updateStatus(Integer id, ProductStatusRequestDTO productStatusRequestDTO) {
        // Validar los datos antes de actualizar
        productStatusValidator.validateBeforeUpdate(id, productStatusRequestDTO);

        // Buscar al estado por su ID
        ProductStatus existingProductStatus = productStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estado con ID: " + id + " no encontrado"));

        // Actualizar los datos del estado
        existingProductStatus.setName(productStatusRequestDTO.getName());

        // Guardar entidad actualizada en la base de datos
        ProductStatus updatedStatus = productStatusRepository.save(existingProductStatus);

        // Convertir entidad a DTO para retornarlo
        return modelMapper.map(updatedStatus, ProductStatusResponseDTO.class);
    }

    // ********** Eliminar un material existente ********** //
    @Override
    public void deleteStatus(Integer id) {
        // Buscar estado por su ID
        ProductStatus existingProductStatus = productStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estado con ID: " + id + " no encotrado"));

        // Eliminar entidad de la base de datos
        productStatusRepository.delete(existingProductStatus);
    }
}
