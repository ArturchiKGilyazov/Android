package ru.nsu.spellit.word;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@Tag(name = "Слова", description = "Взаимодействие со словами")
@Slf4j
public class WordController {
    private final WordService wordService;

    @Operation(
            summary = "Добавление слова",
            description = "Позволяет добавить слово в выбранную категорию"
    )
    @PostMapping("/api/word/{categoryId}")
    public ResponseEntity<Long> addWord(
            @PathVariable @Parameter(description = "Идентификатор категории", required = true) Long categoryId,
            @RequestBody @Parameter(description = "Сущность слова", required = true) WordDto word) throws IOException {
        log.info("adding word : {} in category {}", word.getWordName(), categoryId);
        try {
            Long result = wordService.addWord(word, categoryId);
            if (result == -1) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            log.warn("no category with id : {}", categoryId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Получение слова",
            description = "Позволяет получить слово по идентификатору"
    )
    @GetMapping("/api/word/{wordId}")
    public ResponseEntity<WordDto> getWord(
            @PathVariable @Parameter(description = "Идентификатор слова", required = true) Long wordId) {
        log.info("getting word with id : {}", wordId);
        try {
            return ResponseEntity.ok(wordService.getWordDto(wordId));
        } catch (NoSuchElementException e) {
            log.warn("no word with id : {} ", wordId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/api/word/{wordId}/{userId}")
    public ResponseEntity<WordDto> getUserWord(
            @PathVariable @Parameter(description = "Идентификатор слова", required = true) Long wordId,
            @PathVariable @Parameter(description = "Идентификатор пользователя", required = true) Long userId
    ) {
        log.info("getting word for user : {} with id : {}", userId, wordId);
        try {
            return ResponseEntity.ok(wordService.getWordDto(wordId, userId));
        } catch (NoSuchElementException e) {
            log.info("no word for user : {} with id : {}", userId, wordId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Получение слов",
            description = "Позволяет получить все слова категории"
    )
    @GetMapping("/api/words/{categoryId}")
    public List<WordDto> getWords(
            @PathVariable @Parameter(description = "Идентификатор категории", required = true) Long categoryId
    ) {
        log.info("getting words for category : {}", categoryId);
        return wordService.getWordsDto(categoryId);
    }

    @Operation(
            summary = "Удаление слова из категории",
            description = "Позволяет удалить слово из категории по идентификатору"
    )
    @DeleteMapping("/api/word/{wordId}/{categoryId}")
    public ResponseEntity<Boolean> deleteWordFromCategory(
            @PathVariable @Parameter(description = "Идентификатор слова", required = true) Long wordId,
            @PathVariable @Parameter(description = "Идентификатор категории", required = true) Long categoryId) {
        log.info("deleting word for category : {} with id : {}", categoryId, wordId);
        try {
            wordService.deleteWordFromCategory(wordId, categoryId);
            return ResponseEntity.ok(true);
        } catch (NoSuchElementException e) {
            log.warn("no word for category : {} with id : {}", categoryId, wordId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Обновление слова",
            description = "Позволяет обновить информацию о слове"
    )
    @PatchMapping("/api/word")
    public ResponseEntity<Boolean> updateWord(
            @RequestBody @Parameter(description = "Сущность слова", required = true) WordDto word) throws IOException {
        log.info("updating word with id : {}", word.getWordId());
        try {
            wordService.updateWord(word);
            return ResponseEntity.ok(true);
        } catch (NoSuchElementException e) {
            log.warn("no word with id : {}", word.getWordId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Фиксирование правильности",
            description = "Позволяет увеличить количество раз правильного введения слова"
    )
    @PatchMapping("/api/word/right/{wordId}/{userId}")
    public ResponseEntity incrementWordChecking(
            @PathVariable @Parameter(description = "Идентификатор слова", required = true) Long wordId,
            @PathVariable @Parameter(description = "Идентификатор пользователя", required = true) Long userId) throws IOException {
        log.info("adding one right repeating for word for user : {} with id {}", userId, wordId);
        try {
            Map<Object, Object> response = new HashMap<>();
            wordService.updateWord(wordId, userId);
            response.put("isOk", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            log.warn("no word for user : {} with id {}", userId, wordId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
