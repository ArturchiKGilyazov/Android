package ru.nsu.spellit.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.spellit.category.CategoryController;
import ru.nsu.spellit.category.CategoryDto;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private CategoryController categoryController;

    /**
     * test for checking appropriate user adding to database
     * show that server returned id for new user
     */
    @Test
    @Transactional
    public void addDeleteUser() {
        UserDto testUser = new UserDto();
        testUser.setUsername("testUserLogin");
        testUser.setPassword("testUserPassword");
        Long testUserId = userController.addUser(testUser).getBody();
        Assert.assertNotNull("can't add user or can't get id", testUserId);
    }

    /**
     * test for checking appropriate user adding and getting
     * testUser and user gotten from database must have the same info
     * and gotten user must have one default category
     * show that new user has right info
     */
    @Test
    public void addGetDeleteUser() throws IOException {
        UserDto testUser = new UserDto();
        testUser.setUsername("testUserLogin");
        testUser.setPassword("testUserPassword");
        Long testUserId = userController.addUser(testUser).getBody();
        UserDto gottenUser = userController.getUser(testUserId).getBody();
        Assert.assertEquals(testUserId, gottenUser.getUserId());
        Assert.assertEquals(testUser.getUsername(), gottenUser.getUsername());
        Assert.assertEquals(testUser.getPassword(), gottenUser.getPassword());
        Assert.assertNotNull(gottenUser.getCategories());
        Assert.assertEquals(1, gottenUser.getCategories().size());
        Assert.assertEquals("default", gottenUser.getCategories().get(0).getName());
        Assert.assertTrue(gottenUser.getCategories().get(0).getIsDefault());

        List<CategoryDto> categories = Objects.requireNonNull(userController.getUser(testUserId).getBody()).getCategories();
        for (CategoryDto iterator : categories) {
            Assert.assertEquals(categoryController.deleteCategory(iterator.getCategoryId()).getStatusCodeValue(), 200);
        }
        Assert.assertEquals(userController.deleteUser(testUserId).getStatusCodeValue(), 200);
    }

    @Test
    public void getUsingWrongUserId() {
        ResponseEntity<UserDto> response = userController.getUser(-1L);
        Assert.assertEquals(response.getStatusCodeValue(), 404);
    }

    @Test
    public void getUsingWrongLogin() {
        ResponseEntity<UserDto> response = userController.getUser("wrongUserLogin");
        Assert.assertEquals(response.getStatusCodeValue(), 404);
    }

    @Test
    public void deleteUsingWrongUserId() {
        ResponseEntity<Boolean> response = userController.deleteUser(-1L);
        Assert.assertEquals(response.getStatusCodeValue(), 404);
    }

    @Test
    public void updateUsingWrongUserId() {
        UserDto user = new UserDto(-1L, "noSuchUser", "hereIsPassword", null, 0, 0);
        ResponseEntity<Boolean> response = userController.updateUser(user);
        Assert.assertEquals(response.getStatusCodeValue(), 404);
    }

    /**
     * test for checking appropriate users getting
     * must return as much users as we added
     */
    @Test
    public void addGetDeleteUsers() throws IOException {
        Integer userNum = userController.getUsers().size();
        UserDto testUser1 = new UserDto();
        testUser1.setUsername("testUserLogin1");
        testUser1.setPassword("testUserPassword1");
        Long user1Id = userController.addUser(testUser1).getBody();
        UserDto testUser2 = new UserDto();
        testUser2.setUsername("testUserLogin2");
        testUser2.setPassword("testUserPassword2");
        Long user2Id = userController.addUser(testUser2).getBody();
        UserDto testUser3 = new UserDto();
        testUser3.setUsername("testUserLogin3");
        testUser3.setPassword("testUserPassword3");
        Long user3Id = userController.addUser(testUser3).getBody();
        List<UserDto> users = userController.getUsers();
        Assert.assertEquals(3 + userNum, users.size());


        List<CategoryDto> categories = Objects.requireNonNull(userController.getUser(user1Id).getBody()).getCategories();
        for (CategoryDto iterator : categories) {
            Assert.assertEquals(categoryController.deleteCategory(iterator.getCategoryId()).getStatusCodeValue(), 200);
        }
        Assert.assertEquals(userController.deleteUser(user1Id).getStatusCodeValue(), 200);
        categories = Objects.requireNonNull(userController.getUser(user2Id).getBody()).getCategories();
        for (CategoryDto iterator : categories) {
            Assert.assertEquals(categoryController.deleteCategory(iterator.getCategoryId()).getStatusCodeValue(), 200);
        }
        Assert.assertEquals(userController.deleteUser(user2Id).getStatusCodeValue(), 200);
        categories = Objects.requireNonNull(userController.getUser(user3Id).getBody()).getCategories();
        for (CategoryDto iterator : categories) {
            Assert.assertEquals(categoryController.deleteCategory(iterator.getCategoryId()).getStatusCodeValue(), 200);
        }
        Assert.assertEquals(userController.deleteUser(user3Id).getStatusCodeValue(), 200);
    }

    /**
     * test for checking appropriate user updating
     * must return new right info after updating
     */
    @Test
    public void addUpdateDeleteUser() throws IOException {
        UserDto testUser = new UserDto();
        testUser.setUsername("updateUserTest1");
        testUser.setPassword("Test1");
        Long testUserId = userController.addUser(testUser).getBody();
        testUser.setUsername("updateUserTEST2");
        testUser.setPassword("myPass");
        testUser.setUserId(testUserId);
        userController.updateUser(testUser);
        UserDto gottenUser = userController.getUser(testUserId).getBody();

        Assert.assertEquals(testUserId, gottenUser.getUserId());
        Assert.assertEquals(testUser.getUsername(), gottenUser.getUsername());
        Assert.assertEquals(testUser.getPassword(), gottenUser.getPassword());
        Assert.assertNotNull(gottenUser.getCategories());
        Assert.assertEquals(1, gottenUser.getCategories().size());
        Assert.assertEquals("default", gottenUser.getCategories().get(0).getName());
        Assert.assertTrue(gottenUser.getCategories().get(0).getIsDefault());
        List<CategoryDto> categories = Objects.requireNonNull(userController.getUser(testUserId).getBody()).getCategories();
        for (CategoryDto iterator : categories) {
            Assert.assertEquals(categoryController.deleteCategory(iterator.getCategoryId()).getStatusCodeValue(), 200);
        }
        Assert.assertEquals(userController.deleteUser(testUserId).getStatusCodeValue(), 200);
    }

    @Test
    public void gettingUserByLoginTest() throws IOException {
        UserDto testUser = new UserDto();
        testUser.setUsername("testUserLogin");
        testUser.setPassword("testUserPassword");
        Long testUserId = userController.addUser(testUser).getBody();
        UserDto gottenUser = userController.getUser("testUserLogin").getBody();
        Assert.assertEquals(testUserId, gottenUser.getUserId());
        Assert.assertEquals(testUser.getUsername(), gottenUser.getUsername());
        Assert.assertEquals(testUser.getPassword(), gottenUser.getPassword());
        Assert.assertNotNull(gottenUser.getCategories());
        Assert.assertEquals(1, gottenUser.getCategories().size());
        Assert.assertEquals("default", gottenUser.getCategories().get(0).getName());
        Assert.assertTrue(gottenUser.getCategories().get(0).getIsDefault());

        List<CategoryDto> categories = Objects.requireNonNull(userController.getUser(testUserId).getBody()).getCategories();
        for (CategoryDto iterator : categories) {
            Assert.assertEquals(categoryController.deleteCategory(iterator.getCategoryId()).getStatusCodeValue(), 200);
        }
        Assert.assertEquals(userController.deleteUser(testUserId).getStatusCodeValue(), 200);
    }
}
