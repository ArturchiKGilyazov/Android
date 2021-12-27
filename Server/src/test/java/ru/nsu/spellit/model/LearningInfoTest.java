package ru.nsu.spellit.model;

import org.junit.Assert;
import org.junit.Test;

public class LearningInfoTest {

    @Test
    public void creatingLearningInfo() {
        LearningInfo learningInfo = new LearningInfo();
        Word word = new Word(0L, "word", null, null);
        learningInfo.setWord(word);
        learningInfo.setIsLearned(false);
        learningInfo.setUserId(1L);
        learningInfo.setNumOfRepeating(0);
        learningInfo.setId(2L);

        Long learningInfoId = learningInfo.getId();
        Long userId = learningInfo.getUserId();
        Word addedWord = learningInfo.getWord();
        Integer numOfRepeating = learningInfo.getNumOfRepeating();
        Boolean isLearned = learningInfo.getIsLearned();

        Assert.assertNotNull(learningInfoId);
        Assert.assertNotNull(userId);
        Assert.assertNotNull(addedWord);
        Assert.assertNotNull(numOfRepeating);
        Assert.assertNotNull(isLearned);

        boolean learningInfoIdOk = learningInfoId == 2L;
        boolean userIdOk = userId == 1L;
        boolean numOfRepeatingOk = numOfRepeating == 0;
        boolean wordIsOk = word.equals(addedWord);
        boolean learnedIsOk = !isLearned;

        Assert.assertTrue(learningInfoIdOk);
        Assert.assertTrue(userIdOk);
        Assert.assertTrue(numOfRepeatingOk);
        Assert.assertTrue(wordIsOk);
        Assert.assertTrue(learnedIsOk);
    }

    @Test
    public void creatingLearningInfoAllArgsTest() {
        Word word = new Word(0L, "word", null, null);
        LearningInfo learningInfo = new LearningInfo(2L, 1L, 0, false, word);

        Long learningInfoId = learningInfo.getId();
        Long userId = learningInfo.getUserId();
        Word addedWord = learningInfo.getWord();
        Integer numOfRepeating = learningInfo.getNumOfRepeating();
        Boolean isLearned = learningInfo.getIsLearned();

        Assert.assertNotNull(learningInfoId);
        Assert.assertNotNull(userId);
        Assert.assertNotNull(addedWord);
        Assert.assertNotNull(numOfRepeating);
        Assert.assertNotNull(isLearned);

        boolean learningInfoIdOk = learningInfoId == 2L;
        boolean userIdOk = userId == 1L;
        boolean numOfRepeatingOk = numOfRepeating == 0;
        boolean wordIsOk = word.equals(addedWord);
        boolean learnedIsOk = !isLearned;

        Assert.assertTrue(learningInfoIdOk);
        Assert.assertTrue(userIdOk);
        Assert.assertTrue(numOfRepeatingOk);
        Assert.assertTrue(wordIsOk);
        Assert.assertTrue(learnedIsOk);
    }

    @Test
    public void creatingLearningInfoByBuilder() {
        Word word = new Word(0L, "word", null, null);
        LearningInfo learningInfo = LearningInfo.builder().isLearned(false).id(2L).numOfRepeating(0).word(word).userId(1L).build();

        Long learningInfoId = learningInfo.getId();
        Long userId = learningInfo.getUserId();
        Word addedWord = learningInfo.getWord();
        Integer numOfRepeating = learningInfo.getNumOfRepeating();
        Boolean isLearned = learningInfo.getIsLearned();

        Assert.assertNotNull(learningInfoId);
        Assert.assertNotNull(userId);
        Assert.assertNotNull(addedWord);
        Assert.assertNotNull(numOfRepeating);
        Assert.assertNotNull(isLearned);

        boolean learningInfoIdOk = learningInfoId == 2L;
        boolean userIdOk = userId == 1L;
        boolean numOfRepeatingOk = numOfRepeating == 0;
        boolean wordIsOk = word.equals(addedWord);
        boolean learnedIsOk = !isLearned;

        Assert.assertTrue(learningInfoIdOk);
        Assert.assertTrue(userIdOk);
        Assert.assertTrue(numOfRepeatingOk);
        Assert.assertTrue(wordIsOk);
        Assert.assertTrue(learnedIsOk);
    }

}