package com.hechoconamor.hcaapi.supplies.supply.services.impl;

import com.hechoconamor.hcaapi.supplies.s_category.repository.SupplyCategoryRepository;
import com.hechoconamor.hcaapi.supplies.s_movement.repository.SupplyMovementRepository;
import com.hechoconamor.hcaapi.supplies.s_status.repository.SupplyStatusRepository;
import com.hechoconamor.hcaapi.supplies.supply.dtos.SupplyRequestDTO;
import com.hechoconamor.hcaapi.supplies.supply.dtos.SupplyResponseDTO;
import com.hechoconamor.hcaapi.supplies.supply.entity.Supply;
import com.hechoconamor.hcaapi.supplies.supply.mapper.SupplyMapper;
import com.hechoconamor.hcaapi.supplies.supply.repository.SupplyRepository;
import com.hechoconamor.hcaapi.supplies.supply.services.SupplyService;
import com.hechoconamor.hcaapi.supplies.supply.validator.SupplyValidator;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository supplyRepository;
    private final SupplyCategoryRepository categoryRepository;
    private final SupplyStatusRepository statusRepository;
    private final SupplyMovementRepository movementRepository;

    private final SupplyValidator supplyValidator;
    private final SupplyMapper supplyMapper;

    @Override
    @Transactional
    public SupplyResponseDTO registerSupply(SupplyRequestDTO requestDTO) {
        // Validar los datos antes de registrar
        supplyValidator.validateBeforeRegister(requestDTO);

        // Constuir la entidad
        Supply supply = Supply.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .stock(0) // Temporal, se actualiza después
                .category(categoryRepository.findById(requestDTO.getCategoryId())
                        .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada")))
                .status(statusRepository.findById(requestDTO.getStatusId())
                        .orElseThrow(() -> new NoSuchElementException("Estado no encontrado")))
                .build();

        // Guardar insumo construido
        Supply savedSupply = supplyRepository.save(supply);

        // Calcular y actualizar stock real
        Integer stock = movementRepository.calculateCurrentStock(savedSupply.getId());
        savedSupply.setStock(stock != null ? stock : 0);

        // Guardar insumo en la base de datos
        supplyRepository.save(savedSupply);

        // Convertir entidad a DTO y devolver
        return supplyMapper.toResponseDTO(savedSupply);
    }

    @Override
    public List<SupplyResponseDTO> findAll() {
        List<Supply> supplies = supplyRepository.findAll();

        if (supplies.isEmpty()) {
            throw new NoSuchElementException("No hay insumos registrados.");
        }

        return supplies.stream().map(supply -> {
            Integer stock = movementRepository.calculateCurrentStock(supply.getId());
            supply.setStock(stock != null ? stock : 0); // Si el null entonces devuelve el stock, sino cero
            return supplyMapper.toResponseDTO(supply);
        }).toList();
    }

    @Override
    public SupplyResponseDTO findById(Integer id) {
        // Buscar insumo por su ID
        Supply supply = supplyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Insumo con ID " + id + " no encontrado"));

        // Obtener su stock por medio del ID
        Integer stock = movementRepository.calculateCurrentStock(supply.getId());
        supply.setStock(stock != null ? stock : 0); // Si el null entonces devuelve el stock, sino cero

        // Convertir entidad a DTO y retornarlo
        return supplyMapper.toResponseDTO(supply);
    }

    @Override
    public SupplyResponseDTO findByNameIgnoreCase(String name) {
        // Buscar insumo por su ID
        Supply supply = supplyRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Insumo con nombre '" + name + "' no encontrado"));

        // Obtener su stock por medio del ID
        Integer stock = movementRepository.calculateCurrentStock(supply.getId());
        supply.setStock(stock != null ? stock : 0); // Si el null entonces devuelve el stock, sino cero

        // Convertir entidad a DTO y retornarlo
        return supplyMapper.toResponseDTO(supply);
    }

    @Override
    public SupplyResponseDTO updateSupply(Integer id, SupplyRequestDTO requestDTO) {
        // Validar los datos antes de registrar
        supplyValidator.validateBeforeUpdate(id, requestDTO);

        // Buscar si existe por su ID
        Supply existingSupply = supplyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Insumo con ID " + id + " no encontrado"));

        // Setear los nuevos valores del DTO a la entidad
        existingSupply.setName(requestDTO.getName());
        existingSupply.setDescription(requestDTO.getDescription());
        existingSupply.setCategory(categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada")));
        existingSupply.setStatus(statusRepository.findById(requestDTO.getStatusId())
                .orElseThrow(() -> new NoSuchElementException("Estado no encontrado")));

        // Obtener el stock actual
        Integer stock = movementRepository.calculateCurrentStock(id);
        existingSupply.setStock(stock != null ? stock : 0); // Si el null entonces devuelve el stock, sino cero

        // Guardar cambios en la base de datos
        Supply updatedSupply = supplyRepository.save(existingSupply);

        // Convertir entidad a DTO para retornarlo
        return supplyMapper.toResponseDTO(updatedSupply);
    }

    @Override
    public void deleteSupply(Integer id) {
        // Buscar el insumo por su ID
        Supply supply = supplyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Insumo con ID " + id + " no encontrado"));

        // Eliminar insumo de la base de datos
        supplyRepository.delete(supply);
    }
}
