# 📌 Módulo: User (Usuario)

Este módulo gestiona a los usuarios dentro de la aplicación **API_HechoConAmor**. Permite registrar, consultar, actualizar y eliminar usuarios, asegurando la validación estructural y de negocio. Está diseñado siguiendo los principios de arquitectura limpia, separación de responsabilidades y buenas prácticas en Spring Boot.

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

Ubicación: `com.hechoconamor.hcaapi.user`

```
user/
├── controller/
│   └── UserController.java
├── dtos/
│   ├── UserRequestDTO.java
│   └── UserResponseDTO.java
├── entity/
│   └── User.java
├── repository/
│   └── UserRepository.java
├── services/
│   ├── UserService.java
│   └── impl/
│       └── UserServiceImpl.java
└── validator/
    └── UserValidator.java
```

---

## 📦 DTOs

### ✔️ UserRequestDTO.java
```java
@NotBlank(message = "El nombre de usuario es obligatorio")
@Size(max = 100)
private String name;

@Email(message = "El formato del correo es inválido")
@NotBlank(message = "El correo electrónico es obligatorio")
@Size(max = 100)
private String email;

@NotBlank(message = "La contrasena es obligatoria")
@Size(max = 100)
private String password;

```

### ✔️ UserResponseDTO.java
```java
private Integer id;
private String name;
private String email;
private String roleName;
private LocalDateTime registrationDate;
```

---

## 🧪 Validaciones

- ✅ **Validación estructural** con `Hibernate Validator` (anotaciones en DTO).
- ✅ **Validación de negocio** en `UserValidator`:
  - El nombre de usuario no puede repetirse (case-insensitive).
  - El correo electrónico debe ser único.
  - No se permite crear/actualizar con campos vacíos.

---

## 🔧 Servicio: UserServiceImpl

### Métodos implementados:

| Método                           | Descripción                                 |
|----------------------------------|---------------------------------------------|
| `registerUser(UserRequestDTO)`  | Registra un nuevo usuario                   |
| `updateUser(Integer, DTO)`      | Actualiza datos de un usuario existente     |
| `deleteUser(Integer)`           | Elimina un usuario por ID                   |
| `findById(Integer)`             | Busca usuario por ID                        |
| `findByName(String)`            | Busca usuario por nombre                    |
| `findByEmail(String)`           | Busca usuario por email                     |
| `findByRoleId(Integer)`        | Lista usuarios por ID de rol                |
| `findByRegistrationDate(LocalDate)` | Lista usuarios registrados en una fecha   |
| `findAllUsers()`                | Devuelve todos los usuarios                 |

### Características:

- Trabaja exclusivamente con DTOs.
- Usa `ModelMapper` para mapear entidades <-> DTOs.
- Utiliza `Optional.orElseThrow` para manejar recursos inexistentes.
- Invoca a `UserValidator` antes de guardar o actualizar.
- Lógica desacoplada, clara y orientada a pruebas.

---

## 🌐 Controlador: UserController
```java
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    ...
}
```

Ruta base del controlador: `/api/v1/users`

---

### Endpoints expuestos:

| Método | Ruta                                     | Descripción                            |
|--------|------------------------------------------|----------------------------------------|
| POST   | `/api/v1/users`                          | Registrar un nuevo usuario             |
| GET    | `/api/v1/users`                          | Obtener todos los usuarios             |
| GET    | `/api/v1/users/id/{id}`                  | Obtener usuario por ID                 |
| GET    | `/api/v1/users/name/{name}`              | Obtener usuario por nombre             |
| GET    | `/api/v1/users/email/{email}`            | Obtener usuario por correo electrónico |
| GET    | `/api/v1/users/role/{roleId}`            | Listar usuarios por ID de rol          |
| GET    | `/api/v1/users/registration-date/{date}` | Listar por fecha de registro           |
| PUT    | `/api/v1/users/{id}`                     | Actualizar un usuario existente        |
| DELETE | `/api/v1/users/{id}`                     | Eliminar usuario por ID                |

### Características:

- Utiliza `@Valid` para validar el cuerpo de los request.
- No contiene lógica de negocio.
- Llama a `UserService` y devuelve `ResponseEntity`.
- Usa `@DateTimeFormat` para recibir fechas en el endpoint de registro.

---

## 🛡️ Manejo de errores

Ubicación: `com.hechoconamor.hcaapi.shared.exception.GlobalExceptionHandler`

### Errores manejados:

| Excepción                         | Código HTTP | Descripción                    |
|-----------------------------------|-------------|--------------------------------|
| `BadRequestException`             | 400         | Datos faltantes o inválidos    |
| `ConflictException`               | 409         | Conflicto por datos duplicados |
| `NoSuchElementException`          | 404         | Entidad no encontrada          |
| `MethodArgumentNotValidException` | 400         | Validación estructural fallida |
| `Exception` (genérica)            | 500         | Error interno del servidor     |

#### Ejemplo de error:
```json
{
  "error": "Ya existe un usuario con ese correo electrónico."
}
```

---

## 📐 Decisiones de diseño

- Se evita retornar `Optional` al controlador; se lanza una excepción controlada en su lugar.
- El controlador es **100% limpio**: no contiene reglas de negocio.
- `ModelMapper` está centralizado para evitar redundancia.
- Validaciones están bien separadas en una clase dedicada.
- Uso de DTOs evita exponer entidades directamente.
- `registrationDate` se genera automáticamente con `@CreationTimestamp`.
- `setId(null)` se aplica explícitamente para prevenir errores de Hibernate al registrar.

---

## ✅ Estado actual del módulo

- [x] Entidad `User`
- [x] Repositorio `UserRepository`
- [x] DTOs y validaciones con Hibernate Validator
- [x] Servicio completo con lógica desacoplada
- [x] Validador personalizado `UserValidator`
- [x] Controlador REST (`UserController`)
- [x] Manejo global de excepciones
- [x] Búsqueda por nombre, email, rol y fecha
- [x] Agregar documentación automática con Swagger (`springdoc-openapi`).

---

## 📝 Próximos pasos

- [ ] Encriptar contraseñas con BCrypt en `UserServiceImpl`.
- [ ] Implementar filtros o middleware de auditoría/logging si se desea trazabilidad.