package com.hechoconamor.hcaapi._product.p_color.services.impl;

import com.hechoconamor.hcaapi._product.p_color.dtos.ProductColorRequestDTO;
import com.hechoconamor.hcaapi._product.p_color.dtos.ProductColorResponseDTO;
import com.hechoconamor.hcaapi._product.p_color.entity.ProductColor;
import com.hechoconamor.hcaapi._product.p_color.repository.ProductColorRepository;
import com.hechoconamor.hcaapi._product.p_color.services.ProductColorService;
import com.hechoconamor.hcaapi._product.p_color.validator.ProductColorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductColorServiceImpl implements ProductColorService {

    private final ProductColorRepository productColorRepository;
    private final ProductColorValidator productColorValidator;
    private final ModelMapper modelMapper;

    public ProductColorServiceImpl(ProductColorRepository productColorRepository,
                                   ProductColorValidator productColorValidator,
                                   ModelMapper modelMapper) {
        this.productColorRepository = productColorRepository;
        this.productColorValidator = productColorValidator;
        this.modelMapper = modelMapper;
    }

    // ********** Registrar un nuevo color en el sistema ********** //
    @Override
    public ProductColorResponseDTO registerColor(ProductColorRequestDTO productColorRequestDTO) {
        // Validar el nombre antes de registrar
        productColorValidator.validateBeforeRegister(productColorRequestDTO);

        // Convertir DTO a entidad ProductColor
        ProductColor newProductColor = modelMapper.map(productColorRequestDTO, ProductColor.class);
        newProductColor.setId(null); // Forzar que Hibernate lo trate como un nuevo registro

        // Guardar el nuevo color en la base de datos
        ProductColor savedProductColor = productColorRepository.save(newProductColor);

        // Convertir la entidad a DTO
        return modelMapper.map(savedProductColor, ProductColorResponseDTO.class);
    }

    // ********** Obtener todos los colores registrados ********** //
    @Override
    public List<ProductColorResponseDTO> findAll() {
        return productColorRepository.findAll()
                .stream()
                .map(productColor -> modelMapper.map(productColor, ProductColorResponseDTO.class))
                .collect(Collectors.toList());
    }

    // ********** Buscar color por ID ********** //
    @Override
    public ProductColorResponseDTO findById(Integer id) {
        ProductColor productColor = productColorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Color con ID: " + id + " no encontrado"));

        return modelMapper.map(productColor, ProductColorResponseDTO.class);
    }

    // ********** Buscar color por nombre ********** //
    @Override
    public ProductColorResponseDTO findByName(String name) {
        ProductColor productColor = productColorRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Color con el nombre " + name + " no encontrado"));

        return modelMapper.map(productColor, ProductColorResponseDTO.class);
    }

    // ********** Actualizar el nombre de un color existente ********** //
    @Override
    public ProductColorResponseDTO updateColor(Integer id, ProductColorRequestDTO productColorRequestDTO) {
        // Validar datos antes de actualziar
        productColorValidator.validateBeforeUpdate(id, productColorRequestDTO);

        // Buscar la categoría existente por su ID
        ProductColor existingProductColor = productColorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Color no ID: " + id + " no encontrado"));

        // Actualizar los datos con los nuevos valores del DTO
        existingProductColor.setName(productColorRequestDTO.getName());

        // Guardar cambios en la base de datos
        ProductColor updatedProductColor = productColorRepository.save(existingProductColor);

        // Retornar el color actualizado en forma de DTO
        return modelMapper.map(updatedProductColor, ProductColorResponseDTO.class);
    }

    // ********** Eliminar un material existente ********** //
    @Override
    public void deleteColor(Integer id) {
        // Buscar el color por su ID
        ProductColor productColor = productColorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Color con ID: " + id + " no encontrado"));

        // Eliminar polor de la base de datos
        productColorRepository.delete(productColor);
    }
}
