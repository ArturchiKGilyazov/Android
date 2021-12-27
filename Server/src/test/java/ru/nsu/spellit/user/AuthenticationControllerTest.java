package ru.nsu.spellit.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nsu.spellit.category.CategoryController;
import ru.nsu.spellit.category.CategoryDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationControllerTest {

    @Autowired
    AuthenticationController authenticationController;
    @Autowired
    UserController userController;
    @Autowired
    CategoryController categoryController;

    @Test
    public void registerTest() throws IOException {
        UserDto testUser = new UserDto();
        testUser.setUsername("testUserLogin");
        testUser.setPassword("testUserPassword");
        ResponseEntity<Map<String, String>> response = authenticationController.addUser(testUser);
        Assert.assertEquals(response.getStatusCodeValue(), 200);
        Long testUserId = Long.valueOf(Objects.requireNonNull(response.getBody()).get("userId"));
        Assert.assertNotNull("can't add user or can't get id", testUserId);
        List<CategoryDto> categories = Objects.requireNonNull(userController.getUser(testUserId).getBody()).getCategories();
        for (CategoryDto iterator : categories) {
            Assert.assertEquals(categoryController.deleteCategory(iterator.getCategoryId()).getStatusCodeValue(), 200);
        }
        Assert.assertEquals(userController.deleteUser(testUserId).getStatusCodeValue(), 200);
    }

    @Test
    public void loginTest() throws IOException {
        UserDto testUser = new UserDto();
        String userName = "testUserLogin";
        String userPassword = "testUserPassword";
        testUser.setUsername(userName);
        testUser.setPassword(userPassword);
        ResponseEntity<Map<String, String>> response = authenticationController.addUser(testUser);
        Assert.assertEquals(response.getStatusCodeValue(), 200);
        Long testUserId = Long.valueOf(Objects.requireNonNull(response.getBody()).get("userId"));
        Assert.assertNotNull("can't add user or can't get id", testUserId);

        response = authenticationController.login(testUser);
        Assert.assertNotNull(Objects.requireNonNull(response.getBody()).get("token"));
        Assert.assertEquals(response.getBody().get("username"), userName);
        Assert.assertEquals(response.getBody().get("userId"), testUserId.toString());

        List<CategoryDto> categories = Objects.requireNonNull(userController.getUser(testUserId).getBody()).getCategories();
        for (CategoryDto iterator : categories) {
            Assert.assertEquals(categoryController.deleteCategory(iterator.getCategoryId()).getStatusCodeValue(), 200);
        }
        Assert.assertEquals(userController.deleteUser(testUserId).getStatusCodeValue(), 200);
    }

    @Test
    public void wrongPasswordTest() throws IOException {
        UserDto testUser = new UserDto();
        String userName = "testUserLogin";
        String userPassword = "testUserPassword";
        testUser.setUsername(userName);
        testUser.setPassword(userPassword);
        ResponseEntity<Map<String, String>> response = authenticationController.addUser(testUser);
        Assert.assertEquals(response.getStatusCodeValue(), 200);
        Long testUserId = Long.valueOf(Objects.requireNonNull(response.getBody()).get("userId"));
        Assert.assertNotNull("can't add user or can't get id", testUserId);

        testUser.setPassword("wrongPassword");
        response = authenticationController.login(testUser);
        Assert.assertEquals(response.getStatusCodeValue(), 500);

        List<CategoryDto> categories = Objects.requireNonNull(userController.getUser(testUserId).getBody()).getCategories();
        for (CategoryDto iterator : categories) {
            Assert.assertEquals(categoryController.deleteCategory(iterator.getCategoryId()).getStatusCodeValue(), 200);
        }
        Assert.assertEquals(userController.deleteUser(testUserId).getStatusCodeValue(), 200);
    }

}