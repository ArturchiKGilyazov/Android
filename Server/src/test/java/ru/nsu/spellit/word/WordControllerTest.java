package ru.nsu.spellit.word;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.spellit.category.CategoryController;
import ru.nsu.spellit.category.CategoryDto;
import ru.nsu.spellit.user.UserController;
import ru.nsu.spellit.user.UserDto;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordControllerTest {
    @Autowired
    private WordController wordController;
    @Autowired
    private CategoryController categoryController;
    @Autowired
    private UserController userController;

    @Test
    @Transactional
    public void addWord() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("newUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(1L, "testCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        WordDto word = new WordDto();
        word.setWordName("addTestWord");
        Long testWordId = wordController.addWord(categoryId, word).getBody();
        Assert.assertNotNull(testWordId);
    }

    @Test
    @Transactional
    public void addWordTwoTimes() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("newUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(1L, "testCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        WordDto word = new WordDto();
        word.setWordName("addTestWord");
        wordController.addWord(categoryId, word);
        ResponseEntity<Long> response = wordController.addWord(categoryId, word);
        Assert.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    @Transactional
    public void getWord() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(2L, "testCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        WordDto word = new WordDto();
        word.setWordName("getTestWord");
        Long testWordId = wordController.addWord(categoryId, word).getBody();
        WordDto gottenWord = wordController.getWord(testWordId).getBody();
        Assert.assertEquals(testWordId, gottenWord.getWordId());
        Assert.assertEquals(word.getWordName(), gottenWord.getWordName());
    }

    @Test
    @Transactional
    public void getUserWord() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("getUserTestUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(3L, "getUserTestCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        WordDto word = new WordDto();
        word.setWordName("getUserTestWord");
        word.setLearned(false);
        Long testWordId = wordController.addWord(categoryId, word).getBody();
        WordDto gottenWord = wordController.getUserWord(testWordId, userId).getBody();
        Assert.assertEquals(word.getWordName(), gottenWord.getWordName());
        Assert.assertFalse(gottenWord.getLearned());
    }

    @Test
    @Transactional
    public void getUserWordWhichDoNotExists() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("getUserTestUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(3L, "getUserTestCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        WordDto word = new WordDto();
        word.setWordName("getUserTestWord");
        word.setLearned(false);
        Long testWordId = wordController.addWord(categoryId, word).getBody();
        ResponseEntity<WordDto> response = wordController.getUserWord(-1L, userId);
        Assert.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @Transactional
    public void getWords() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("getAllTestUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(4L, "getAllTestCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        WordDto word1 = new WordDto();
        word1.setWordName("testWord1");
        WordDto word2 = new WordDto();
        word1.setWordName("testWord2");
        wordController.addWord(categoryId, word1);
        wordController.addWord(categoryId, word2);
        List<WordDto> words = wordController.getWords(categoryId);
        Assert.assertEquals(2, words.size());
    }

    @Test
    @Transactional
    public void deleteWordFromCategory() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("deleteTestUsername");
        user.setPassword("deleteTestPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(5L, "deleteTestCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        WordDto word = new WordDto();
        word.setWordName("deleteTestWord");
        Long testWordId = wordController.addWord(categoryId, word).getBody();
        wordController.deleteWordFromCategory(testWordId, categoryId).getBody();
        WordDto gottenWord = wordController.getWord(testWordId).getBody();
        Assert.assertNull(gottenWord);
    }

    @Test
    @Transactional
    public void updateWord() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("updateTestUsername");
        user.setPassword("updateTestPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(5L, "deleteTestCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        WordDto word = new WordDto();
        word.setWordName("testWord");
        word.setLearned(false);
        Long testWordId = wordController.addWord(categoryId, word).getBody();
        WordDto updateWord = new WordDto();
        updateWord.setWordId(testWordId);
        updateWord.setWordName("updateWord");
        updateWord.setNumOfRepeating(0);
        Boolean update = wordController.updateWord(updateWord).getBody();
        wordController.incrementWordChecking(updateWord.getWordId(), userId);
        wordController.incrementWordChecking(updateWord.getWordId(), userId);
        wordController.incrementWordChecking(updateWord.getWordId(), userId);
        WordDto gottenWord = wordController.getUserWord(testWordId, userId).getBody();
        Assert.assertTrue(update);
        Assert.assertTrue(gottenWord.getLearned());
    }

    @Test
    @Transactional
    public void updateWordWhichDoesNotExists() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("updateTestUsername");
        user.setPassword("updateTestPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(5L, "deleteTestCategory", wordDtoList, true);
        WordDto word = new WordDto();
        word.setWordName("testWord");
        word.setLearned(false);
        WordDto updateWord = new WordDto();
        updateWord.setWordId(-1L);
        updateWord.setWordName("updateWord");
        updateWord.setLearned(true);
        ResponseEntity<Boolean> response = wordController.updateWord(updateWord);
        Assert.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @Transactional
    public void incrementWordChecking() throws IOException {
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

        ResponseEntity<WordDto> response = wordController.getUserWord(testWordId, userId);
        int numOfRepeating = 0;
        boolean isLearned = false;
        if (response.getBody().getNumOfRepeating() != null) {
            numOfRepeating = response.getBody().getNumOfRepeating();
        }
        if (response.getBody().getLearned() != null) {
            isLearned = response.getBody().getLearned();
        }
        Assert.assertEquals(numOfRepeating, 3);
        Assert.assertTrue(isLearned);
    }
}

