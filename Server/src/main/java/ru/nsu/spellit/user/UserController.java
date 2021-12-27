package ru.nsu.spellit.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@Tag(name = "Пользователи", description = "Взаимодействие с пользователями ")
@Slf4j
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Добавление пользователя"
    )
    @PostMapping("/api/user")
    public ResponseEntity<Long> addUser(
            @RequestBody @Parameter(description = "Сущность пользователя", required = true) UserDto user) {
        log.info("adding user : {} ", user.getUsername());
        try {
            return ResponseEntity.ok(userService.addUser(user));
        } catch (NoSuchElementException e) {
            log.warn("user with login : {} already exists", user.getUsername());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Получение пользователя",
            description = "Позволяет получить пользователя по идентификатору"
    )
    @GetMapping("/api/user/{userId}")
    public ResponseEntity<UserDto> getUser(
            @PathVariable @Parameter(description = "Идентификатор пользователя", required = true) Long userId) {
        log.info("getting user by id : {} ", userId);
        try {
            return ResponseEntity.ok(userService.getUserDto(userId));
        } catch (NoSuchElementException e) {
            log.warn("no user with id : {} ", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @Operation(
            summary = "Получение пользователя",
            description = "Позволяет получить пользователя по логину"
    )
    @GetMapping("/api/user")
    public ResponseEntity<UserDto> getUser(
            @RequestBody @Parameter(description = "Логин пользователя", required = true)String username) {
        log.info("getting user with login : {} ", username);
        try {
            return ResponseEntity.ok(userService.getUserDto(username));
        } catch (NoSuchElementException e) {
            log.warn("no user with login : {} ", username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Получение пользователей",
            description = "Позволяет получить всех пользователей системы"
    )
    @GetMapping("/api/users")
    public List<UserDto> getUsers(
    ) {
        log.info("someone getting all users");
        return userService.getUsers();
    }

    @Operation(
            summary = "Удаление пользователя",
            description = "Позволяет удалить пользователя по идентификатору"
    )
    @DeleteMapping("/api/user/{userId}")
    public ResponseEntity<Boolean> deleteUser(
            @PathVariable @Parameter(description = "Идентификатор пользователя", required = true) Long userId) {
        log.info("deleting user with id : {}", userId);
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(true);
        } catch (NoSuchElementException e) {
            log.warn("no user with id : {} ", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Обновление пользователя",
            description = "Позволяет обновить информацию о пользователе. Предпочтительно добавлять категории через специальные методы"
    )
    @PatchMapping("/api/user")
    public ResponseEntity<Boolean> updateUser(
            @RequestBody @Parameter(description = "Сущность пользователя", required = true) UserDto user) {
        log.info("updating user : {}", user.getUsername());
        try {
            userService.updateUser(user);
            return ResponseEntity.ok(true);
        } catch (NoSuchElementException e) {
            log.warn("no user with id : {}", user.getUserId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}


