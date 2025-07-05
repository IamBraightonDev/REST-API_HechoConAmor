package com.hechoconamor.hcaapi.products.product.services.impl;

import com.hechoconamor.hcaapi.products.inventory_movement.repository.InventoryMovementRepository;
import com.hechoconamor.hcaapi.products.p_category.repository.ProductCategoryRepository;
import com.hechoconamor.hcaapi.products.p_color.repository.ProductColorRepository;
import com.hechoconamor.hcaapi.products.p_material.repository.ProductMaterialRepository;
import com.hechoconamor.hcaapi.products.product.dtos.ProductRequestDTO;
import com.hechoconamor.hcaapi.products.product.dtos.ProductResponseDTO;
import com.hechoconamor.hcaapi.products.product.entity.Product;
import com.hechoconamor.hcaapi.products.product.mapper.ProductMapper;
import com.hechoconamor.hcaapi.products.product.repository.ProductRepository;
import com.hechoconamor.hcaapi.products.product.services.ProductService;
import com.hechoconamor.hcaapi.products.product.validator.ProductValidator;
import com.hechoconamor.hcaapi.products.p_size.repository.ProductSizeRepository;
import com.hechoconamor.hcaapi.products.p_status.repository.ProductStatusRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final InventoryMovementRepository inventoryMovementRepository;
    private final ProductValidator productValidator;
    private final ProductMapper productMapper;

    private final ProductCategoryRepository categoryRepository;
    private final ProductColorRepository colorRepository;
    private final ProductMaterialRepository materialRepository;
    private final ProductSizeRepository sizeRepository;
    private final ProductStatusRepository statusRepository;

    // ********** Registrar un nuevo producto ********** //
    @Override
    @Transactional
    public ProductResponseDTO registerProduct(ProductRequestDTO requestDTO) {
        // Validaciones
        productValidator.validateBeforeRegister(requestDTO);

        // Seteamos los valores
        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setDescription(requestDTO.getDescription());
        product.setPrice(requestDTO.getPrice());
        product.setId(null);

        // Asignar stock inicial en 0 (muy importante para evitar error 500)
        product.setStock(0);

        // Seteamos relaciones manualmente
        product.setCategory(categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada")));
        product.setColor(colorRepository.findById(requestDTO.getColorId())
                .orElseThrow(() -> new NoSuchElementException("Color no encontrado")));
        product.setMaterial(materialRepository.findById(requestDTO.getMaterialId())
                .orElseThrow(() -> new NoSuchElementException("Material no encontrado")));
        product.setSize(sizeRepository.findById(requestDTO.getSizeId())
                .orElseThrow(() -> new NoSuchElementException("Tamaño no encontrado")));
        product.setStatus(statusRepository.findById(requestDTO.getStatusId())
                .orElseThrow(() -> new NoSuchElementException("Estado no encontrado")));

        // Guardamos y retornamos DTO
        Product savedProduct = productRepository.save(product);

        // Calcular stock del producto
        Integer stock = inventoryMovementRepository.findStockByProductId(savedProduct.getId());

        // Mapeo manual a DTO limpio (sin .toString)
        return productMapper.toResponseDTO(savedProduct, stock);
    }

    // ********** Obtener todos los productos ********** //
    @Override
    public List<ProductResponseDTO> findAll() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new NoSuchElementException("No hay productos registrados en el sistema.");
        }

        return products.stream()
                .map(product -> {
                    Integer stock = inventoryMovementRepository.findStockByProductId(product.getId());
                    return productMapper.toResponseDTO(product, stock);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDTO> findAllByCategory(String categoryName) {
        List<Product> products = productRepository.findByCategory_NameIgnoreCase(categoryName);

        if (products.isEmpty()) {
            throw new NoSuchElementException("No se encontraron productos con la categoría: " + categoryName);
        }

        return products.stream()
                .map(product -> {
                    Integer stock = inventoryMovementRepository.findStockByProductId(product.getId());
                    return productMapper.toResponseDTO(product, stock);
                })
                .collect(Collectors.toList());
    }

    // ********** Buscar producto por ID ********** //
    @Override
    public ProductResponseDTO findById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto con ID: " + id + " no encontrado"));

        // Calcular el stock
        Integer stock = inventoryMovementRepository.findStockByProductId(id);

        // Mapeo manual
        return productMapper.toResponseDTO(product, stock);
    }

    // ********** Buscar producto por nombre ********** //
    @Override
    public ProductResponseDTO findByName(String name) {
        Product product = productRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Producto con nombre '" + name + "' no encontrado"));

        // Calcular el stock
        Integer stock = inventoryMovementRepository.findStockByProductId(product.getId());

        // Mapeo manual
        return productMapper.toResponseDTO(product, stock);
    }

    // ********** Actualizar producto ********** //
    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Integer id, ProductRequestDTO requestDTO) {
        // Validaciones
        productValidator.validateBeforeUpdate(id, requestDTO);

        // Buscar si existe por su ID
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto con ID: " + id + " no encontrado"));

        // Actualizar campos
        existingProduct.setName(requestDTO.getName());
        existingProduct.setDescription(requestDTO.getDescription());
        existingProduct.setPrice(requestDTO.getPrice());

        // Calcular el stock
        Integer stock = inventoryMovementRepository.findStockByProductId(id);

        // Relaciones
        existingProduct.setCategory(categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada")));
        existingProduct.setColor(colorRepository.findById(requestDTO.getColorId())
                .orElseThrow(() -> new NoSuchElementException("Color no encontrado")));
        existingProduct.setMaterial(materialRepository.findById(requestDTO.getMaterialId())
                .orElseThrow(() -> new NoSuchElementException("Material no encontrado")));
        existingProduct.setSize(sizeRepository.findById(requestDTO.getSizeId())
                .orElseThrow(() -> new NoSuchElementException("Tamaño no encontrado")));
        existingProduct.setStatus(statusRepository.findById(requestDTO.getStatusId())
                .orElseThrow(() -> new NoSuchElementException("Estado no encontrado")));

        // Guardar los cambios en la base de datos y retornar el DTO
        Product updatedProduct = productRepository.save(existingProduct);

        // Mapeo manual a DTO limpio (sin .toString)
        return productMapper.toResponseDTO(updatedProduct, stock);
    }

    // ********** Eliminar producto ********** //
    @Override
    @Transactional
    public void delete(Integer id) {
        // Buscar producto por su ID
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto con ID: " + id + " no encontrado"));

        // Eliminar producto
        productRepository.delete(product);
    }

}
