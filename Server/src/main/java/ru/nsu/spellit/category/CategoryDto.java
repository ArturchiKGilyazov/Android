package ru.nsu.spellit.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import ru.nsu.spellit.word.WordDto;

import java.util.List;

@Schema(description = "Сущность категории")
@Builder
@RequiredArgsConstructor
public class CategoryDto {
    @Getter
    @Nullable
    private final Long categoryId;

    @Getter
    private final String name;

    @Getter
    private final List<WordDto> words;

    @Getter
    private final Boolean isDefault;

}
