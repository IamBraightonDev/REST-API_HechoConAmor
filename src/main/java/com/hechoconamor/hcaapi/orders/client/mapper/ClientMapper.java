package com.hechoconamor.hcaapi.orders.client.mapper;

import com.hechoconamor.hcaapi.orders.client.dtos.ClientRequestDTO;
import com.hechoconamor.hcaapi.orders.client.dtos.ClientResponseDTO;
import com.hechoconamor.hcaapi.orders.client.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequestDTO dto) {
        return Client.builder()
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .build();
    }

    public void updateEntity(Client entity, ClientRequestDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
        entity.setDireccion(dto.getDireccion());
    }

    public ClientResponseDTO toResponseDTO(Client entity) {
        return ClientResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .direccion(entity.getDireccion())
                .build();
    }
}