package ru.nsu.spellit.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.spellit.model.User;
import ru.nsu.spellit.user.UserService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@Tag(name = "Категории", description = "Взаимодействие с категориями пользователей")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;

    @Operation(
            summary = "Добавление категории",
            description = "Позволяет добавить категорию выбранному пользователю"

    )
    @PostMapping("/api/category/{userId}")
    public ResponseEntity<Long> addCategory(
            @PathVariable @Parameter(description = "Идентификатор пользователя", required = true) Long userId,
            @RequestBody @Parameter(description = "Сущность категории", required = true) CategoryDto category) {
        log.info("creating category : {} for user : {}", category.getName(), userId);
        try {
            Long categoryId = categoryService.addCategory(category, userId);
            if (categoryId == -1L) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.ok(categoryId);
        } catch (NoSuchElementException e) {
            log.warn("no such user : {} ", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Получение категории",
            description = "Позволяет получить категорию по идентификатору"
    )
    @GetMapping("/api/category/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(
            @PathVariable @Parameter(description = "Идентификатор категории", required = true) Long categoryId) {
        log.info("client wants to get category : {} ", categoryId);
        try {
            return ResponseEntity.ok(categoryService.getCategoryDto(categoryId));
        } catch (NoSuchElementException e) {
            log.warn("no such category : {} ", categoryId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Получение категории по умолчанию",
            description = "Позволяет получить основную категорию пользователя"
    )
    @GetMapping("/api/category/default/{userId}")
    public ResponseEntity<CategoryDto> getDefaultCategory(
            @PathVariable @Parameter(description = "Идентификатор пользователя", required = true) Long userId
    ) {
        log.info("user : {} wants to get default category", userId);
        User user = userService.getUser(userId);
        try {
            return ResponseEntity.ok(categoryService.getDefaultCategory(user));
        } catch (NoSuchElementException e) {
            log.warn("no such user : {} ", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Получение категорий",
            description = "Позволяет получить все категории пользователя"
    )
    @GetMapping("/api/categories/{userId}")
    public List<CategoryDto> getCategories(
            @PathVariable @Parameter(description = "Идентификатор пользователя", required = true) Long userId
    ) {
        log.info("user : {} wants to get all his categories ", userId);
        User user = userService.getUser(userId);
        return categoryService.getCategoriesDto(user);
    }

    @Operation(
            summary = "Удаление категории",
            description = "Позволяет удалить категорию по идентификатору"
    )
    @DeleteMapping("/api/category/{categoryId}")
    public ResponseEntity<Boolean> deleteCategory(
            @PathVariable @Parameter(description = "Идентификатор категории", required = true) Long categoryId) throws IOException {
        log.info("client wants to delete category : {}", categoryId);
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok(true);
        } catch (NoSuchElementException e) {
            log.warn("no such category : {} ", categoryId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Обновление категории",
            description = "Позволяет обновить информацию о категории. Предпочтительно добавлять слова через специальные методы"
    )
    @PatchMapping("/api/category")
    public ResponseEntity<Boolean> updateCategory(
            @RequestBody @Parameter(description = "Сущность категории", required = true) CategoryDto category) {
        log.info("client wants to update category : {} ", category.getCategoryId());
        try {
            categoryService.updateCategory(category);
            return ResponseEntity.ok(true);
        } catch (NoSuchElementException e) {
            log.info("no such category : {} ", category.getCategoryId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
