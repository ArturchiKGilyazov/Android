package ru.nsu.spellit.user.security.jwt;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nsu.spellit.category.CategoryController;
import ru.nsu.spellit.category.CategoryDto;
import ru.nsu.spellit.user.AuthenticationController;
import ru.nsu.spellit.user.UserController;
import ru.nsu.spellit.user.UserDto;

import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTokenProviderTest {

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationController authenticationController;
    @Autowired
    UserController userController;
    @Autowired
    CategoryController categoryController;

    @Test
    public void creatingTokensTest() {
        String token = jwtTokenProvider.createToken("testUserName");
        Assert.assertNotNull(token);
        String userName = jwtTokenProvider.getUsername(token);
        Assert.assertEquals(userName, "testUserName");
    }

    @Test
    public void tokensTest() throws IOException {
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
        String token = response.getBody().get("token");
        Assert.assertNotNull(token);
        Assert.assertEquals(response.getBody().get("username"), userName);
        Assert.assertEquals(response.getBody().get("userId"), testUserId.toString());

        Assert.assertEquals(jwtTokenProvider.getUsername(token), userName);
        Assert.assertTrue(jwtTokenProvider.validateToken(token));
        Assert.assertNotNull(jwtTokenProvider.getAuthentication(token));
        Assert.assertFalse(jwtTokenProvider.validateToken("Bearer_" + token));

        List<CategoryDto> categories = Objects.requireNonNull(userController.getUser(testUserId).getBody()).getCategories();
        for (CategoryDto iterator : categories) {
            Assert.assertEquals(categoryController.deleteCategory(iterator.getCategoryId()).getStatusCodeValue(), 200);
        }
        Assert.assertEquals(userController.deleteUser(testUserId).getStatusCodeValue(), 200);
    }


}