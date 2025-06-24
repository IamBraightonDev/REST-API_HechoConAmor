package com.hechoconamor.hcaapi.user.controller;

import com.hechoconamor.hcaapi.shared.exceptions.ForbiddenException;
import com.hechoconamor.hcaapi.user.dtos.UserRequestDTO;
import com.hechoconamor.hcaapi.user.dtos.UserResponseDTO;
import com.hechoconamor.hcaapi.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userDto) {
        UserResponseDTO newUser = userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers()); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id)); // 200 OK
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponseDTO> findByName(@Valid @PathVariable String name) {
        return ResponseEntity.ok(userService.findByName(name)); // 200 OK
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> findByEmail(@Valid @PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email)); // 200 OK
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<UserResponseDTO>> findByRoleId(@Valid @PathVariable Integer roleId) {
        return ResponseEntity.ok(userService.findByRoleId(roleId)); // 200 OK
    }

    @GetMapping("/registration-date/{date}")
    public ResponseEntity<List<UserResponseDTO>> findByRegistrationDate(@PathVariable
                                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                            LocalDate date) {
        return ResponseEntity.ok(userService.findByRegistrationDate(date)); // 200 OK
    }


    // ************************ Update - CRUD ************************ //
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id,
                                                      @Valid @RequestBody UserRequestDTO userDto) {
        UserResponseDTO updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser); // 200 OK
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<Void> changeUserRole(@PathVariable Integer id,
                                               @RequestParam Integer newRoleId,
                                               @RequestParam String currentUserRole) {
        // Verificación básica sin seguridad real
        if (!"Admin".equalsIgnoreCase(currentUserRole)) {
            throw new ForbiddenException("Solo los administradores pueden cambiar el rol de un usuario.");
        }

        userService.changeUserRole(id, newRoleId);
        return ResponseEntity.noContent().build();
    }


    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 204 Not Found
    }
}
