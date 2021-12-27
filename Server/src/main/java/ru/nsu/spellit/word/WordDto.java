package ru.nsu.spellit.word;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Schema(description = "Сущность слова")
@Builder
public class WordDto {
    @Getter
    @Setter
    @Nullable
    private Long wordId;

    @Getter
    @Setter
    private String wordName;

    @Getter
    @Setter
    private Integer numOfRepeating;

    @Getter
    @Setter
    private Boolean learned;

    public WordDto() {
    }

    public WordDto(Long wordId, String wordName, Integer numOfRepeating, Boolean learned) {
        this.setWordId(wordId);
        this.setWordName(wordName);
        this.setNumOfRepeating(numOfRepeating);
        this.setLearned(learned);
    }

    public WordDto(Long wordId, String wordName, Integer numOfRepeating) {
        this.setWordId(wordId);
        this.setWordName(wordName);
        this.setNumOfRepeating(numOfRepeating);
    }
}
