package com.hechoconamor.hcaapi.orders.client.services;

import com.hechoconamor.hcaapi.orders.client.dtos.ClientRequestDTO;
import com.hechoconamor.hcaapi.orders.client.dtos.ClientResponseDTO;

import java.util.List;

public interface ClientService {

    ClientResponseDTO create(ClientRequestDTO dto);

    ClientResponseDTO update(Integer id, ClientRequestDTO dto);

    ClientResponseDTO getById(Integer id);

    List<ClientResponseDTO> getAll();

    void delete(Integer id);

}
