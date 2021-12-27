package ru.nsu.spellit.model;

import org.junit.Assert;
import org.junit.Test;

public class WordTest {

    @Test
    public void createWord() {
        Word word = new Word();
        word.setWordId(0L);
        word.setWordName("word");
        word.setCategories(null);
        word.setLearningInfo(null);

        Assert.assertNotNull(word.getWordId());
        Assert.assertNotNull(word.getWordName());
        Assert.assertNull(word.getCategories());
        Assert.assertNull(word.getLearningInfo());

        boolean par1IsOk = word.getWordId() == 0L;
        boolean par2IsOk = word.getWordName().equals("word");
        boolean par3IsOk = word.getCategories() == null;
        boolean par4IsOk = word.getLearningInfo() == null;

        Assert.assertTrue(par1IsOk);
        Assert.assertTrue(par2IsOk);
        Assert.assertTrue(par3IsOk);
        Assert.assertTrue(par4IsOk);
    }

    @Test
    public void createWordAllArgsConstructor() {
        Word word = new Word(0L, "word", null, null);

        Assert.assertNotNull(word.getWordId());
        Assert.assertNotNull(word.getWordName());
        Assert.assertNull(word.getCategories());
        Assert.assertNull(word.getLearningInfo());

        boolean par1IsOk = word.getWordId() == 0L;
        boolean par2IsOk = word.getWordName().equals("word");
        boolean par3IsOk = word.getCategories() == null;
        boolean par4IsOk = word.getLearningInfo() == null;

        Assert.assertTrue(par1IsOk);
        Assert.assertTrue(par2IsOk);
        Assert.assertTrue(par3IsOk);
        Assert.assertTrue(par4IsOk);
    }

    @Test
    public void createWordByBuilder() {
        Word word = Word.builder().wordId(0L).wordName("word").categories(null).learningInfo(null).build();

        Assert.assertNotNull(word.getWordId());
        Assert.assertNotNull(word.getWordName());
        Assert.assertNull(word.getCategories());
        Assert.assertNull(word.getLearningInfo());

        boolean par1IsOk = word.getWordId() == 0L;
        boolean par2IsOk = word.getWordName().equals("word");
        boolean par3IsOk = word.getCategories() == null;
        boolean par4IsOk = word.getLearningInfo() == null;

        Assert.assertTrue(par1IsOk);
        Assert.assertTrue(par2IsOk);
        Assert.assertTrue(par3IsOk);
        Assert.assertTrue(par4IsOk);
    }

}