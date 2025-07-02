package com.hechoconamor.hcaapi.orders.client.services.impl;

import com.hechoconamor.hcaapi.orders.client.dtos.ClientRequestDTO;
import com.hechoconamor.hcaapi.orders.client.dtos.ClientResponseDTO;
import com.hechoconamor.hcaapi.orders.client.entity.Client;
import com.hechoconamor.hcaapi.orders.client.mapper.ClientMapper;
import com.hechoconamor.hcaapi.orders.client.repository.ClientRepository;
import com.hechoconamor.hcaapi.orders.client.services.ClientService;
import com.hechoconamor.hcaapi.orders.client.validator.ClientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientValidator clientValidator;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponseDTO create(ClientRequestDTO dto) {
        clientValidator.validateOnCreate(dto);
        Client client = clientMapper.toEntity(dto);
        return clientMapper.toResponseDTO(clientRepository.save(client));
    }

    @Override
    public ClientResponseDTO update(Integer id, ClientRequestDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));

        clientValidator.validateOnUpdate(id, dto);
        clientMapper.updateEntity(client, dto);
        return clientMapper.toResponseDTO(clientRepository.save(client));
    }

    @Override
    public ClientResponseDTO getById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));
        return clientMapper.toResponseDTO(client);
    }

    @Override
    public List<ClientResponseDTO> getAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (!clientRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente no encontrado");
        }
        clientRepository.deleteById(id);
    }
}