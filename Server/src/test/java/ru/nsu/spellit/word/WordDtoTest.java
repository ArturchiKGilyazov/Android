package ru.nsu.spellit.word;

import org.junit.Assert;
import org.junit.Test;

public class WordDtoTest {

    @Test
    public void creatingWordDtoTest1() {
        WordDto word = new WordDto();
        word.setWordId(0L);
        word.setLearned(false);
        word.setWordName("word");
        word.setNumOfRepeating(0);

        Assert.assertNotNull(word.getWordId());
        Assert.assertNotNull(word.getNumOfRepeating());
        Assert.assertNotNull(word.getWordName());
        Assert.assertNotNull(word.getLearned());

        boolean wordIdIsOk = word.getWordId() == 0L;
        boolean wordNameIsOk = word.getWordName().equals("word");
        boolean wordLearnedIsOk = word.getLearned().equals(false);
        boolean wordNumOfRepeatingIsOk = word.getNumOfRepeating().equals(0);

        Assert.assertTrue(wordIdIsOk);
        Assert.assertTrue(wordLearnedIsOk);
        Assert.assertTrue(wordNameIsOk);
        Assert.assertTrue(wordNumOfRepeatingIsOk);
    }

    @Test
    public void creatingWordDtoTest2() {
        WordDto word = new WordDto(0L, "word", 0, false);

        Assert.assertNotNull(word.getWordId());
        Assert.assertNotNull(word.getNumOfRepeating());
        Assert.assertNotNull(word.getWordName());
        Assert.assertNotNull(word.getLearned());

        boolean wordIdIsOk = word.getWordId() == 0L;
        boolean wordNameIsOk = word.getWordName().equals("word");
        boolean wordLearnedIsOk = word.getLearned().equals(false);
        boolean wordNumOfRepeatingIsOk = word.getNumOfRepeating().equals(0);

        Assert.assertTrue(wordIdIsOk);
        Assert.assertTrue(wordLearnedIsOk);
        Assert.assertTrue(wordNameIsOk);
        Assert.assertTrue(wordNumOfRepeatingIsOk);
    }

    @Test
    public void creatingWordDtoTest3() {
        WordDto word = new WordDto(0L, "word", 0);

        Assert.assertNotNull(word.getWordId());
        Assert.assertNotNull(word.getNumOfRepeating());
        Assert.assertNotNull(word.getWordName());

        boolean wordIdIsOk = word.getWordId() == 0L;
        boolean wordNameIsOk = word.getWordName().equals("word");
        boolean wordNumOfRepeatingIsOk = word.getNumOfRepeating().equals(0);

        Assert.assertTrue(wordIdIsOk);
        Assert.assertTrue(wordNameIsOk);
        Assert.assertTrue(wordNumOfRepeatingIsOk);
    }
}