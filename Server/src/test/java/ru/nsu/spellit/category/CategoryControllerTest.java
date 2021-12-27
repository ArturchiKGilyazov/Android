package ru.nsu.spellit.category;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.spellit.user.UserController;
import ru.nsu.spellit.user.UserDto;
import ru.nsu.spellit.word.WordDto;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {
    @Autowired
    private UserController userController;
    @Autowired
    private CategoryController categoryController;

    @Test
    public void addCategory() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(1L, "testCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        Assert.assertNotNull(categoryId);
        categoryController.deleteCategory(categoryId);
        userController.deleteUser(userId);
    }

    @Test
    @Transactional
    public void addCategoryTwoTimes() {
        UserDto user = new UserDto();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(1L, "testCategory", wordDtoList, true);
        categoryController.addCategory(userId, category);
        ResponseEntity<Long> response = categoryController.addCategory(userId, category);
        Assert.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void getCategory() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(1L, "testCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        CategoryDto gottenCategory = categoryController.getCategory(categoryId).getBody();
        Assert.assertEquals(categoryId.longValue(), gottenCategory.getCategoryId().longValue());
        Assert.assertEquals("testCategory", gottenCategory.getName());
        Assert.assertTrue(gottenCategory.getIsDefault());
        Assert.assertNotNull(gottenCategory.getWords());
        categoryController.deleteCategory(categoryId);
        userController.deleteUser(userId);
    }

    @Test
    public void getDefaultCategory() {
        UserDto user = new UserDto();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        CategoryDto gottenCategory = categoryController.getDefaultCategory(userId).getBody();
        Assert.assertEquals("default", gottenCategory.getName());
        Assert.assertTrue(gottenCategory.getIsDefault());
        userController.deleteUser(userId);
    }

    @Test
    public void getCategories() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(1L, "testCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        List<CategoryDto> categories = categoryController.getCategories(userId);
        Assert.assertEquals(2, categories.size());
        categoryController.deleteCategory(categoryId);
        userController.deleteUser(userId);

    }

    @Test
    public void deleteCategory() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(1L, "testCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        categoryController.deleteCategory(categoryId);
        CategoryDto gottenCategory = categoryController.getCategory(categoryId).getBody();
        Assert.assertNull(gottenCategory);
        userController.deleteUser(userId);
    }

    @Test
    public void updateCategory() throws IOException {
        UserDto user = new UserDto();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        Long userId = userController.addUser(user).getBody();
        List<WordDto> wordDtoList = new LinkedList<>();
        CategoryDto category = new CategoryDto(1L, "testCategory", wordDtoList, true);
        Long categoryId = categoryController.addCategory(userId, category).getBody();
        CategoryDto updateCategory = new CategoryDto(categoryId, "updateCategory", wordDtoList, false);
        Boolean update = categoryController.updateCategory(updateCategory).getBody();
        CategoryDto gottenUpdateCategory = categoryController.getCategory(categoryId).getBody();
        Assert.assertTrue(update);
        Assert.assertEquals(updateCategory.getName(), gottenUpdateCategory.getName());
        Assert.assertEquals(category.getWords(), gottenUpdateCategory.getWords());
        Assert.assertEquals(updateCategory.getIsDefault(), gottenUpdateCategory.getIsDefault());
        categoryController.deleteCategory(categoryId);
        userController.deleteUser(userId);
    }
}
