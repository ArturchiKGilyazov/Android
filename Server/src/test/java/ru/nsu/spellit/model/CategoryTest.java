package ru.nsu.spellit.model;

import org.junit.Assert;
import org.junit.Test;

public class CategoryTest {

    @Test
    public void createCategory() {
        User user = new User(0L, "userName", "password", null, 0, 0);
        Category category = new Category();
        category.setCategoryId(0L);
        category.setIsDefault(false);
        category.setWords(null);
        category.setName("category");
        category.setUser(user);

        Assert.assertNotNull(category.getCategoryId());
        Assert.assertNotNull(category.getName());
        Assert.assertNotNull(category.getUser());
        Assert.assertNotNull(category.getIsDefault());
        Assert.assertNull(category.getWords());

        boolean par1IsOk = category.getCategoryId() == 0L;
        boolean par2IsOk = !category.getIsDefault();
        boolean par3IsOk = category.getName().equals("category");
        boolean par4IsOk = category.getUser().equals(user);
        boolean par5IsOk = category.getWords() == null;

        Assert.assertTrue(par1IsOk);
        Assert.assertTrue(par2IsOk);
        Assert.assertTrue(par3IsOk);
        Assert.assertTrue(par4IsOk);
        Assert.assertTrue(par5IsOk);
    }

    @Test
    public void createCategoryAllArgsConstructor() {
        User user = new User(0L, "userName", "password", null, 0, 0);
        Category category = new Category(0L, "category", null, user, false);

        Assert.assertNotNull(category.getCategoryId());
        Assert.assertNotNull(category.getName());
        Assert.assertNotNull(category.getUser());
        Assert.assertNotNull(category.getIsDefault());
        Assert.assertNull(category.getWords());

        boolean par1IsOk = category.getCategoryId() == 0L;
        boolean par2IsOk = !category.getIsDefault();
        boolean par3IsOk = category.getName().equals("category");
        boolean par4IsOk = category.getUser().equals(user);
        boolean par5IsOk = category.getWords() == null;

        Assert.assertTrue(par1IsOk);
        Assert.assertTrue(par2IsOk);
        Assert.assertTrue(par3IsOk);
        Assert.assertTrue(par4IsOk);
        Assert.assertTrue(par5IsOk);
    }

    @Test
    public void createCategoryByBuilder() {
        User user = new User(0L, "userName", "password", null, 0, 0);
        Category category = Category.builder().categoryId(0L).name("category").words(null).user(user).isDefault(false).build();

        Assert.assertNotNull(category.getCategoryId());
        Assert.assertNotNull(category.getName());
        Assert.assertNotNull(category.getUser());
        Assert.assertNotNull(category.getIsDefault());
        Assert.assertNull(category.getWords());

        boolean par1IsOk = category.getCategoryId() == 0L;
        boolean par2IsOk = !category.getIsDefault();
        boolean par3IsOk = category.getName().equals("category");
        boolean par4IsOk = category.getUser().equals(user);
        boolean par5IsOk = category.getWords() == null;

        Assert.assertTrue(par1IsOk);
        Assert.assertTrue(par2IsOk);
        Assert.assertTrue(par3IsOk);
        Assert.assertTrue(par4IsOk);
        Assert.assertTrue(par5IsOk);
    }
}