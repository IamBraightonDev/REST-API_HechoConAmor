package com.hechoconamor.hcaapi.supplies.s_status.service.impl;

import com.hechoconamor.hcaapi.supplies.s_status.dtos.SupplyStatusRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_status.dtos.SupplyStatusResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_status.entity.SupplyStatus;
import com.hechoconamor.hcaapi.supplies.s_status.mapper.SupplyStatusMapper;
import com.hechoconamor.hcaapi.supplies.s_status.repository.SupplyStatusRepository;
import com.hechoconamor.hcaapi.supplies.s_status.service.SupplyStatusService;
import com.hechoconamor.hcaapi.supplies.s_status.validator.SupplyStatusValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SupplyStatusServiceImpl implements SupplyStatusService {

    private SupplyStatusRepository statusRepository;
    private SupplyStatusValidator statusValidator;
    private SupplyStatusMapper statusMapper;

    public SupplyStatusServiceImpl(SupplyStatusRepository statusRepository,
                                   SupplyStatusValidator statusValidator,
                                   SupplyStatusMapper statusMapper) {
        this.statusRepository = statusRepository;
        this.statusValidator = statusValidator;
        this.statusMapper = statusMapper;
    }

    @Override
    public SupplyStatusResponseDTO registerStatus(SupplyStatusRequestDTO requestDTO) {
        // Validar los datos antes de registrar
        statusValidator.validateBeforeRegister(requestDTO);

        // Convertir el DTO a entidad
        SupplyStatus newStatus = statusMapper.toEntity(requestDTO);
        newStatus.setId(null);

        // Guardar en la base de datos
        SupplyStatus savedStatud = statusRepository.save(newStatus);

        // Convertir entidad a DTO para retornar
        return statusMapper.toDTO(savedStatud);
    }

    @Override
    public List<SupplyStatusResponseDTO> findAll() {
        return statusRepository.findAll()
                .stream()
                .map(statusMapper::toDTO)
                .toList();
    }

    @Override
    public SupplyStatusResponseDTO findById(Integer id) {
        SupplyStatus supplyStatus = statusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estado con ID: " + id + " no encontrado"));
        return statusMapper.toDTO(supplyStatus);
    }

    @Override
    public SupplyStatusResponseDTO findByNameIgnoreCase(String name) {
        SupplyStatus supplyStatus = statusRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Estado con nombre " + name + " no encontrado"));
        return statusMapper.toDTO(supplyStatus);
    }

    @Override
    public SupplyStatusResponseDTO updateStatus(Integer id, SupplyStatusRequestDTO requestDTO) {
        // Validar los datos antes de actualizar
        statusValidator.validateBeforeUpdate(id, requestDTO);

        // Buscar estado existente por ID
        SupplyStatus existingStatus = statusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estado con ID: " + id + " no encontrado"));

        // Setear nuevos valores
        existingStatus.setName(requestDTO.getName());

        // Guardar en la base de datos
        SupplyStatus updatedStatus = statusRepository.save(existingStatus);

        // Convertir entidad a DTO para retornar
        return statusMapper.toDTO(updatedStatus);
    }

    @Override
    public void deleteStatus(Integer id) {
        // Buscar por ID
        SupplyStatus existingStatus = statusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estado con ID: " + id + " no encontrado"));

        // Eliminar el estado de la base de datos
        statusRepository.delete(existingStatus);
    }
}
