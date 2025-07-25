package com.hechoconamor.hcaapi.orders.client.repository;

import com.hechoconamor.hcaapi.orders.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByNombreIgnoreCase(String nombre);

}
