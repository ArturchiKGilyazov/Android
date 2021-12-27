package ru.nsu.spellit.learningInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.spellit.category.CategoryController;
import ru.nsu.spellit.category.CategoryDto;
import ru.nsu.spellit.model.LearningInfo;
import ru.nsu.spellit.model.Word;
import ru.nsu.spellit.user.UserController;
import ru.nsu.spellit.user.UserDto;
import ru.nsu.spellit.word.WordController;
import ru.nsu.spellit.word.WordDto;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningInfoRepositoryTest {
    @Autowired
    LearningInfoRepository learningInfoRepository;
    @Autowired
    private WordController wordController;
    @Autowired
    private CategoryController categoryController;
    @Autowired
    private UserController userController;

    @Test
    @Transactional
    public void learningTest() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(5L, "deleteTestCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        WordDto word = new WordDto();
        word.setWordName("testWord");
        word.setLearned(false);
        Long testWordId = wordController.addWord(categoryId, word).getBody();

        wordController.incrementWordChecking(testWordId,userId);
        wordController.incrementWordChecking(testWordId,userId);
        wordController.incrementWordChecking(testWordId,userId);

        Word realWord = new Word();
        realWord.setWordId(testWordId);

        LearningInfo info = learningInfoRepository.findByUserIdAndWord(userId, realWord);
        Assert.assertNotNull(info);
        Assert.assertEquals(info.getUserId(), userId);
    }

}