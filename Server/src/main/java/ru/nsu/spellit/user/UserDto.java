package ru.nsu.spellit.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import ru.nsu.spellit.category.CategoryDto;

import java.util.List;

@Schema(description = "Сущность пользователя")
@Builder
public class UserDto {
    @Getter
    @Setter
    @Nullable
    private Long userId;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private List<CategoryDto> categories;

    @Getter
    @Setter
    private Integer numOfRepeatingToKnow = 3;

    @Getter
    @Setter
    private Integer numOfLearnedWords = 0;

    public UserDto() {

    }

    public UserDto(Long userId, String username, String password, List<CategoryDto> categories, Integer numOfRepeatingToKnow, Integer numOfLearnedWords) {
        setUserId(userId);
        setUsername(username);
        setPassword(password);
        setCategories(categories);
        setNumOfRepeatingToKnow(numOfRepeatingToKnow);
        setNumOfLearnedWords(numOfLearnedWords);
    }
}
