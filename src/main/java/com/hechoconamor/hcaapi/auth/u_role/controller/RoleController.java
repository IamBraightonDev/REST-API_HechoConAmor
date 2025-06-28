package com.hechoconamor.hcaapi.auth.u_role.controller;

import com.hechoconamor.hcaapi.auth.u_role.dtos.RoleRequestDTO;
import com.hechoconamor.hcaapi.auth.u_role.dtos.RoleResponseDTO;
import com.hechoconamor.hcaapi.auth.u_role.services.RoleService;
import jakarta.validation.Valid;
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

    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<RoleResponseDTO> registerRole(@Valid @RequestBody RoleRequestDTO requestDto) {
        RoleResponseDTO savedRole = roleService.registerRole(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> finAllRoles() {
        return ResponseEntity.ok(roleService.findAllRoles()); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RoleResponseDTO> findById(@PathVariable Integer id) {
        RoleResponseDTO foundRole = roleService.findById(id);
        return ResponseEntity.ok(foundRole); // 200 OK
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RoleResponseDTO> findByName(@PathVariable String name) {
        RoleResponseDTO foundRole = roleService.findByNameIgnoreCase(name);
        return ResponseEntity.ok(foundRole); // 200 OK
    }


    // ************************ Update - CRUD ************************ //
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable Integer id,
                                                      @Valid @RequestBody RoleRequestDTO requestDto) {
        RoleResponseDTO updatedRole = roleService.updateRole(id, requestDto);
        return ResponseEntity.ok(updatedRole); // 200 OK
    }


    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
