
# 📌 Módulo: Role (Rol)

Este módulo gestiona los roles dentro de la aplicación API_HechoConAmor. Es responsable de crear, consultar, actualizar y eliminar roles. Toda la lógica de negocio, validación y manejo de errores se encuentra desacoplada del controlador siguiendo buenas prácticas.

---

## 🧪 Tecnologías utilizadas

- Spring Boot 3.5.0
- Spring Data JPA
- PostgreSQL
- Hibernate Validator
- ModelMapper
- Java 17
- Lombok
- IntelliJ IDEA

---

## 🧱 Estructura del paquete

Ubicación: `com.hechoconamor.hcaapi.role`

```
role/
├── controller/
│   └── RoleController.java
├── dtos/
│   ├── RoleRequestDTO.java
│   └── RoleResponseDTO.java
├── entity/
│   └── Role.java
├── repository/
│   └── RoleRepository.java
├── services/
│   ├── RoleService.java
│   └── impl/
│       └── RoleServiceImpl.java
└── validator/
    └── RoleValidator.java
```

---

## 📦 DTOs

### ✔️ RoleRequestDTO.java
```java
@NotBlank(message = "El nombre no puede estar vacío")
private String name;
```

### ✔️ RoleResponseDTO.java
```java
private Integer id;
private String name;
```

---

## 🧪 Validaciones

- Validación estructural con Hibernate Validator (@NotBlank).
- Validación de negocio en `RoleValidator`:
  - El nombre no puede estar vacío.
  - El nombre debe ser único (ignora mayúsculas/minúsculas).

---

## 🔧 Servicio: RoleServiceImpl

Métodos implementados:
- `registerRole(RoleRequestDTO dto)`
- `updateRole(Integer id, RoleRequestDTO dto)`
- `deleteRole(Integer id)`
- `getRoleById(Integer id)`
- `getAllRoles()`

Cada método:
- Trabaja solo con DTOs.
- Usa `ModelMapper` para convertir entidades a DTOs.
- Llama a `RoleValidator` antes de guardar/actualizar.
- Lanza excepciones personalizadas si corresponde.
- Usa `Optional.orElseThrow()` internamente para manejar entidades no encontradas.

---

## 🌐 Controlador: RoleController
```java
@RestController
@RequestMapping("/api/roles")
public class RoleController {
    ...
}
```
Ruta base del controlador: `/api/roles`

---

#### Endpoints expuestos:

| Método | Ruta                     | Descripción              |
|--------|--------------------------|--------------------------|
| POST   | `/api/roles`             | Registrar un nuevo rol   |
| GET    | `/api/roles`             | Obtener todos los roles  |
| GET    | `/api/roles/id/{id}`     | Obtener rol por ID       |
| GET    | `/api/roles/name/{name}` | Obtener rol por nombre   |
| PUT    | `/api/roles/{id}`        | Actualizar rol existente |
| DELETE | `/api/roles/{id}`        | Eliminar rol por ID      |

#### Características:
- Usa `@Valid` en los request body.
- No contiene lógica de negocio.
- Llama al servicio y retorna `ResponseEntity`.

---

## 🛡️ Manejo de errores

Ubicación: `com.hechoconamor.hca.api.shared.exception.GlobalExceptionHandler`

Errores manejados:

| Excepción                         | Código HTTP | Descripción                         |
|-----------------------------------|-------------|-------------------------------------|
| `BadRequestException`             | 400         | Datos inválidos                     |
| `ConflictException`               | 409         | Conflicto (por ejemplo, duplicados) |
| `NoSuchElementException`          | 404         | Recurso no encontrado               |
| `MethodArgumentNotValidException` | 400         | Error de validación de campos       |
| `Exception` (genérica)            | 500         | Error interno del servidor          |

Ejemplo de error:
```json
{
  "error": "Ya existe un rol con ese nombre."
}
```

---

## 📐 Decisiones de diseño

- No se usa `Optional` como retorno, ya que las excepciones controlan el flujo.
- El `Controller` está limpio; toda lógica va en `Service` y `Validator`.
- `ModelMapper` es global y centralizado.
- No se incluye `List<User>` en la entidad `Role`; esa lógica se maneja desde `User`.
- Se sigue el patrón DTO → Validator → Service → Repository.

---

## ✅ Estado actual del módulo

- [x] Entidad Role
- [x] Repositorio RoleRepository
- [x] DTOs y validaciones
- [x] Servicio y lógica de negocio
- [x] Validador separado
- [x] Controlador RESTful
- [x] Manejo global de errores

---

## 📝 Próximo paso

Implementar el módulo `User` siguiendo exactamente la misma estructura y estándares definidos aquí.
