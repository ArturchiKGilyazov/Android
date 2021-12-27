package ru.nsu.spellit.word;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.spellit.category.CategoryRepository;
import ru.nsu.spellit.learningInfo.LearningInfoRepository;
import ru.nsu.spellit.model.Category;
import ru.nsu.spellit.model.LearningInfo;
import ru.nsu.spellit.model.User;
import ru.nsu.spellit.model.Word;
import ru.nsu.spellit.user.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WordService {
    private final WordRepository wordRepository;
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;
    private final LearningInfoRepository learningInfoRepository;

    private final WordMapper mapper;

    public Long addWord(WordDto wordDto, Long categoryId) throws IOException {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) throw new NoSuchElementException("category");
        Word word = wordRepository.findByWordName(wordDto.getWordName());
        if (word == null) {
            word = mapper.getWordFromWordDto(wordDto);
        } else {
            Long userId = category.getUser().getUserId();
            LearningInfo isAlreadyLearning = learningInfoRepository.findByUserIdAndWord(userId, word);
            if (isAlreadyLearning != null) {
                return -1L;
            }
        }
        word.addCategory(category);
        LearningInfo learningInfo = new LearningInfo();
        learningInfo.setNumOfRepeating(0);
        learningInfo.setUserId(category.getUser().getUserId());
        learningInfo.setWord(word);
        word.addLearningInfo(learningInfo);

        word = wordRepository.save(word);
        category.addWord(word);
        learningInfo.setWord(word);
        categoryRepository.save(category);
        learningInfoRepository.save(learningInfo);
        return word.getWordId();
    }

    public WordDto getWordDto(Long wordId) {
        return mapper.getWordDtoFromWord(getWordFromDB(wordId));
    }

    public WordDto getWordDto(Long wordId, Long userId) {
        Word word = getWordFromDB(wordId);
        LearningInfo info = learningInfoRepository.findByUserIdAndWord(userId, word);
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) throw new NoSuchElementException("user");
        Integer numRep = user.getNumOfRepeatingToKnow();
        return mapper.getWordDtoFromWord(word, info.getNumOfRepeating(), numRep);
    }

    public void deleteWord(Long wordId) {
        wordRepository.deleteById(wordId);
    }

    public void deleteWordFromCategory(Long wordId, Long categoryId) {
        Word word = wordRepository.findById(wordId).orElse(null);
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (word == null || category == null) return;
        category.deleteWord(word);
        categoryRepository.save(category);
        if (word.getCategories().size() == 1 && word.getCategories().get(0).equals(category))
            deleteWord(wordId);
        else {
            word.deleteCategory(category);
            updateWord(word);
        }
    }

    public void updateWord(WordDto wordDto) {
        Word word = getWordFromDB(wordDto.getWordId());
        word.setWord(mapper.getWordFromWordDto(wordDto));
        wordRepository.save(word);
    }

    public void updateWord(Word word) {
        Word updatedWord = getWordFromDB(word.getWordId());
        updatedWord.setWord(word);
        wordRepository.save(word);
    }

    public void updateWord(Long wordId, Long userId) {
        Word word = getWordFromDB(wordId);
        LearningInfo info = learningInfoRepository.findByUserIdAndWord(userId, word);
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        info.setNumOfRepeating(info.getNumOfRepeating() + 1);
        learningInfoRepository.save(info);
        if (user.getNumOfRepeatingToKnow().equals(info.getNumOfRepeating())) {
            if (!info.getIsLearned()) {
                user.setNumOfLearnedWords(user.getNumOfLearnedWords() + 1);
                userRepository.save(user);
            }
            info.setIsLearned(true);
            learningInfoRepository.save(info);
            return;
        }
    }

    public List<WordDto> getWordsDto(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null)
            return wordRepository.findAll().stream().map(mapper::getWordDtoFromWord).collect(Collectors.toList());
        List<Word> words = wordRepository.findAllByCategoriesContains(category);
        Long userId = category.getUser().getUserId();
        List<WordDto> wordDtos = new ArrayList<>();
        for (Word iterator : words) {
            LearningInfo info = learningInfoRepository.findByUserIdAndWord(userId, iterator);
            wordDtos.add(new WordDto(iterator.getWordId(), iterator.getWordName(), info.getNumOfRepeating(), info.getIsLearned()));
        }
        return wordDtos;
    }

    Word getWordFromDB(Long wordId) {
        Word word = wordRepository.findById(wordId).orElse(null);
        if (word == null) throw new NoSuchElementException("word");
        return word;
    }
}
