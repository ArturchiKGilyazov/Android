package ru.nsu.spellit.word;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.spellit.model.Word;

@Component
@RequiredArgsConstructor
public class WordMapper {

    public WordDto getWordDtoFromWord(Word word) {
        return WordDto.builder()
                .wordId(word.getWordId())
                .wordName(word.getWordName())
                .build();
    }

    public WordDto getWordDtoFromWord(Word word, Integer repeating) {
        return WordDto.builder()
                .wordId(word.getWordId())
                .wordName(word.getWordName())
                .numOfRepeating(repeating)
                .learned(false)
                .build();
    }

    public WordDto getWordDtoFromWord(Word word, Integer repeating, Integer neededNumRepeating) {
        return WordDto.builder()
                .wordId(word.getWordId())
                .wordName(word.getWordName())
                .numOfRepeating(repeating)
                .learned(neededNumRepeating.equals(repeating))
                .build();
    }

    public Word getWordFromWordDto(WordDto wordDto) {
        return Word.builder().
                wordId(wordDto.getWordId()).
                wordName(wordDto.getWordName()).
                build();
    }
}
