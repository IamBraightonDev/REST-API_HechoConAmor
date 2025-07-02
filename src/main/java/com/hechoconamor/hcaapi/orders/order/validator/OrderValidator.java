package com.hechoconamor.hcaapi.orders.order.validator;

import com.hechoconamor.hcaapi.orders.client.entity.Client;
import com.hechoconamor.hcaapi.orders.client.repository.ClientRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final ClientRepository clientRepository;

    public Client validateClientExists(Integer clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new BadRequestException("Cliente no encontrado"));
    }
}