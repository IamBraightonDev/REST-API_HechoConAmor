package com.hechoconamor.hcaapi.orders.client.validator;

import com.hechoconamor.hcaapi.orders.client.dtos.ClientRequestDTO;
import com.hechoconamor.hcaapi.orders.client.repository.ClientRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private final ClientRepository clientRepository;

    public void validateBeforeRegister(ClientRequestDTO requestDTO) {
        // Validar nombre
        if(requestDTO.getNombre() == null || requestDTO.getNombre().isBlank()) {
            throw new BadRequestException("El nombre de usuario no puede estar vacío.");
        }
    }

    public void validateBeforeUpdate(ClientRequestDTO requestDTO) {
        // Validar nombre
        if (requestDTO.getNombre() == null || requestDTO.getNombre().isBlank()) {
            throw new BadRequestException("El nombre de usuario no puede estar vacío.");
        }
    }
}