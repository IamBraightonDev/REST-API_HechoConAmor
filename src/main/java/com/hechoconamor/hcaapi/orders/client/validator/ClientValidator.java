package com.hechoconamor.hcaapi.orders.client.validator;

import com.hechoconamor.hcaapi.orders.client.dtos.ClientRequestDTO;
import com.hechoconamor.hcaapi.orders.client.entity.Client;
import com.hechoconamor.hcaapi.orders.client.repository.ClientRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private final ClientRepository clientRepository;

    public void validateOnCreate(ClientRequestDTO dto) {
        if (clientRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("Ya existe un cliente con ese email");
        }
    }

    public void validateOnUpdate(Integer id, ClientRequestDTO dto) {
        Client existing = clientRepository.findByEmail(dto.getEmail())
                .filter(c -> !c.getId().equals(id))
                .orElse(null);

        if (existing != null) {
            throw new BadRequestException("Ya existe otro cliente con ese email");
        }
    }
}