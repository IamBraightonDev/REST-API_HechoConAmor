package com.hechoconamor.hcaapi._product.p_category.services.impl;

import com.hechoconamor.hcaapi._product.p_category.dtos.ProductCategoryRequestDTO;
import com.hechoconamor.hcaapi._product.p_category.dtos.ProductCategoryResponseDTO;
import com.hechoconamor.hcaapi._product.p_category.entity.ProductCategory;
import com.hechoconamor.hcaapi._product.p_category.repository.ProductCategoryRepository;
import com.hechoconamor.hcaapi._product.p_category.services.ProductCategoryService;
import com.hechoconamor.hcaapi._product.p_category.validator.ProductCategoryValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryValidator productCategoryValidator;
    private final ModelMapper modelMapper;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository,
                                      ProductCategoryValidator productCategoryValidator,
                                      ModelMapper modelMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryValidator = productCategoryValidator;
        this.modelMapper = modelMapper;
    }

    // ********** Registrar una nueva categoria en el sistema ********** //
    @Override
    public ProductCategoryResponseDTO registerCategory(ProductCategoryRequestDTO productCategoryRequestDTO) {
        // Validamos las reglas de negocio antes del registro
        productCategoryValidator.validateBeforeRegister(productCategoryRequestDTO);

        // Convertir el DTO a una entidad ProductCategory
        ProductCategory newProductCategory = modelMapper.map(productCategoryRequestDTO, ProductCategory.class);
        newProductCategory.setId(null); // Forzar que Hibernate lo trate como un nuevo registro

        // Guardar la nueva categoria en la base de datos
        ProductCategory savedProductCategory = productCategoryRepository.save(newProductCategory);

        // Convertir la entidad guardada a un DTO de respuesta y retornarla
        return modelMapper.map(savedProductCategory, ProductCategoryResponseDTO.class);
    }


    // ********** Obtener todas las categorías registradas ********** //
    @Override
    public List<ProductCategoryResponseDTO> findAll() {
        return productCategoryRepository.findAll()
                .stream()
                .map(productCategory -> modelMapper.map(productCategory, ProductCategoryResponseDTO.class))
                .collect(Collectors.toList());
    }

    // ********** Buscar categoria por ID ********** //
    @Override
    public ProductCategoryResponseDTO findById(Integer id) {
        ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoría con ID: " + id + " no encontrada"));

        return modelMapper.map(productCategory, ProductCategoryResponseDTO.class);
    }

    // ********** Buscar categoria por nombre ********** //
    @Override
    public ProductCategoryResponseDTO findByNameIgnoreCase(String name) {
        ProductCategory productCategory = productCategoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Categoría con nombre: " + name + " no encontrada"));
        return modelMapper.map(productCategory, ProductCategoryResponseDTO.class);
    }


    // ********** Actualizar nombre de una categoría existente ********** //
    @Override
    public ProductCategoryResponseDTO updatedCategory(Integer id, ProductCategoryRequestDTO productCategoryRequestDTO) {
        // Validar datos antes de actualizar
        productCategoryValidator.validateBeforeUpdate(id, productCategoryRequestDTO);

        // Buscar la categoría existente por su ID
        ProductCategory existingProductCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoría con ID: " + id + " no encontrada"));

        // Actualizar los datos con los nuevos valores del DTO
        existingProductCategory.setName(productCategoryRequestDTO.getName());

        // Guardar cambios en la base de datos
        ProductCategory updatedProductCategory = productCategoryRepository.save(existingProductCategory);

        // Retornar la categoría actualizada en forma de DTO
        return modelMapper.map(updatedProductCategory, ProductCategoryResponseDTO.class);
    }


    // ********** Eliminar una categoría por su ID ********** //
    @Override
    public void deleteCategory(Integer id) {
        // Buscar la categoría por su ID
        ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoría con ID: " + id + " no encontrada"));

        // Eliminar la categoría de la base de datos
        productCategoryRepository.delete(productCategory);
    }
}
