package com.hechoconamor.hca.api.repositories;

import com.hechoconamor.hca.api.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    // Read - CRUD
    @Query(value = "EXEC SP_ObtenerTodosLosUsuarios", nativeQuery = true)
    List<User> obtenerTodosLosUsuarios();

    @Query(value = "EXEC SP_ObtenerUsuarioPorId", nativeQuery = true)
    List<User> obtenerUsuarioPorId();

    @Query(value = "EXEC SP_ObtenerIdPorNombreDeUsuario", nativeQuery = true)
    List<User> obtenerIdPorNombreDeUsuario();

}
