package ru.nsu.spellit.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.spellit.learningInfo.LearningInfoService;
import ru.nsu.spellit.model.Category;
import ru.nsu.spellit.model.Word;
import ru.nsu.spellit.word.WordDto;
import ru.nsu.spellit.word.WordMapper;
import ru.nsu.spellit.word.WordRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CategoryMapper {
    private final WordMapper wordMapper;
    private final LearningInfoService learningInfoService;
    private final WordRepository wordRepository;

    public CategoryDto getCategoryDto(Category category) {
        CategoryDto.CategoryDtoBuilder categoryDto = CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .isDefault(category.getIsDefault());
        List<WordDto> wordDtoList = new LinkedList<>();
        if (category.getWords() != null)
            for (Word word : category.getWords()) {
                Integer repeatNum = learningInfoService.getLearningInfo(category.getUser().getUserId(), wordRepository.findById(word.getWordId()).orElse(null)).getNumOfRepeating();
                WordDto wordDto = wordMapper.getWordDtoFromWord(word, repeatNum);
                wordDtoList.add(wordDto);
            }
        categoryDto.words(wordDtoList);
        return categoryDto.build();
    }

    public Category getCategory(CategoryDto categoryDto) {
        Category.CategoryBuilder category = Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .name(categoryDto.getName())
                .isDefault(categoryDto.getIsDefault());
        if (categoryDto.getWords() != null)
            category.words(
                    categoryDto.getWords()
                            .stream()
                            .map(wordMapper::getWordFromWordDto)
                            .collect(Collectors.toList()));
        return category.build();
    }

}
