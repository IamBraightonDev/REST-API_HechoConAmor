package com.hechoconamor.hca.api.controllers;

import com.hechoconamor.hca.api.models.Role;
import com.hechoconamor.hca.api.models.User;
import com.hechoconamor.hca.api.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> registerRole(@RequestBody Role role) {
        Role nuevoRole = roleService.registerRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRole);
    }

    @GetMapping
    public ResponseEntity<List<Role>> finAllRoles() {
        List<Role> listaRoles = roleService.findAllRoles();
        return ResponseEntity.ok(listaRoles);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Role> findById(@PathVariable Integer id) {
        return roleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Role> findByName(@PathVariable String nombre) {
        return roleService.findByNameIgnoreCase(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping("{name}/users")
    public ResponseEntity<List<User>> findUsersByRoleName(@PathVariable String name) {
        return roleService.findByNameIgnoreCase(name)
                .map(role -> ResponseEntity.ok(role.getUserList()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Integer id, @RequestBody Role role) {
        return roleService.updateRole(id, role)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable Integer id) {
        boolean deletedRole = roleService.deleteRole(id);
        return deletedRole ? ResponseEntity.noContent().build()  // 204 No Content
                            : ResponseEntity.notFound().build(); // 404 Not Found
    }
}
