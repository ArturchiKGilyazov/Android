package ru.nsu.spellit.model;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void creatingUserTest() {
       User user = new User();
       user.setUserId(0L);
       user.setPassword("password");
       user.setUsername("userName");
       user.setNumOfLearnedWords(0);
       user.setNumOfRepeatingToKnow(0);
       user.setCategories(null);

        Assert.assertNotNull(user.getUserId());
        Assert.assertNotNull(user.getUsername());
        Assert.assertNotNull(user.getPassword());
        Assert.assertNotNull(user.getNumOfRepeatingToKnow());
        Assert.assertNotNull(user.getNumOfLearnedWords());
        Assert.assertNull(user.getCategories());

        boolean par1IsOk = user.getUserId() == 0L;
        boolean par2IsOk = user.getCategories() == null;
        boolean par3IsOk = user.getUsername().equals("userName");
        boolean par4IsOk = user.getPassword().equals("password");
        boolean par5IsOk = user.getNumOfLearnedWords() == 0;
        boolean par6IsOk = user.getNumOfRepeatingToKnow() == 0;

        Assert.assertTrue(par1IsOk);
        Assert.assertTrue(par2IsOk);
        Assert.assertTrue(par3IsOk);
        Assert.assertTrue(par4IsOk);
        Assert.assertTrue(par5IsOk);
        Assert.assertTrue(par6IsOk);
    }

    @Test
    public void creatingUserTestAllArgsConstructor() {
        User user = new User(0L, "userName", "password", null, 0, 0);

        Assert.assertNotNull(user.getUserId());
        Assert.assertNotNull(user.getUsername());
        Assert.assertNotNull(user.getPassword());
        Assert.assertNotNull(user.getNumOfRepeatingToKnow());
        Assert.assertNotNull(user.getNumOfLearnedWords());
        Assert.assertNull(user.getCategories());

        boolean par1IsOk = user.getUserId() == 0L;
        boolean par2IsOk = user.getCategories() == null;
        boolean par3IsOk = user.getUsername().equals("userName");
        boolean par4IsOk = user.getPassword().equals("password");
        boolean par5IsOk = user.getNumOfLearnedWords() == 0;
        boolean par6IsOk = user.getNumOfRepeatingToKnow() == 0;

        Assert.assertTrue(par1IsOk);
        Assert.assertTrue(par2IsOk);
        Assert.assertTrue(par3IsOk);
        Assert.assertTrue(par4IsOk);
        Assert.assertTrue(par5IsOk);
        Assert.assertTrue(par6IsOk);
    }

    @Test
    public void creatingUserByBuilderTest() {
        User user = new User.UserBuilder().userId(0L).numOfRepeatingToKnow(0).numOfLearnedWords(0).username("userName").password("password").categories(null).build();

        Assert.assertNotNull(user.getUserId());
        Assert.assertNotNull(user.getUsername());
        Assert.assertNotNull(user.getPassword());
        Assert.assertNotNull(user.getNumOfRepeatingToKnow());
        Assert.assertNotNull(user.getNumOfLearnedWords());
        Assert.assertNull(user.getCategories());

        boolean par1IsOk = user.getUserId() == 0L;
        boolean par2IsOk = user.getCategories() == null;
        boolean par3IsOk = user.getUsername().equals("userName");
        boolean par4IsOk = user.getPassword().equals("password");
        boolean par5IsOk = user.getNumOfLearnedWords() == 0;
        boolean par6IsOk = user.getNumOfRepeatingToKnow() == 0;

        Assert.assertTrue(par1IsOk);
        Assert.assertTrue(par2IsOk);
        Assert.assertTrue(par3IsOk);
        Assert.assertTrue(par4IsOk);
        Assert.assertTrue(par5IsOk);
        Assert.assertTrue(par6IsOk);
    }

}