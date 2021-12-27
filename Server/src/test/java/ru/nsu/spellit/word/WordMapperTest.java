package ru.nsu.spellit.word;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.spellit.model.Word;

public class WordMapperTest {

    @Test
    public void wordDtoToWordTest() {
        WordDto wordDto = new WordDto();
        wordDto.setWordId(0L);
        wordDto.setLearned(false);
        wordDto.setWordName("word");
        wordDto.setNumOfRepeating(0);

        Word word = new WordMapper().getWordFromWordDto(wordDto);

        Assert.assertNotNull(word.getWordId());
        Assert.assertNotNull(word.getWordName());

        boolean wordIdIsOk = word.getWordId() == 0L;
        boolean wordNameIsOk = word.getWordName().equals("word");

        Assert.assertTrue(wordIdIsOk);
        Assert.assertTrue(wordNameIsOk);
    }

    @Test
    public void wordToWordDtoAllArgs() {
        Word word = new Word(0L, "word", null, null);

        WordDto wordDto = new WordMapper().getWordDtoFromWord(word, 0, 3);

        Assert.assertNotNull(wordDto.getWordId());
        Assert.assertNotNull(wordDto.getNumOfRepeating());
        Assert.assertNotNull(wordDto.getWordName());
        Assert.assertNotNull(wordDto.getLearned());

        boolean wordIdIsOk = wordDto.getWordId() == 0L;
        boolean wordNameIsOk = wordDto.getWordName().equals("word");
        boolean wordLearnedIsOk = wordDto.getLearned().equals(false);
        boolean wordNumOfRepeatingIsOk = wordDto.getNumOfRepeating().equals(0);

        Assert.assertTrue(wordIdIsOk);
        Assert.assertTrue(wordLearnedIsOk);
        Assert.assertTrue(wordNameIsOk);
        Assert.assertTrue(wordNumOfRepeatingIsOk);
    }

    @Test
    public void wordToWordDtoSomeArgs() {
        Word word = new Word(0L, "word", null, null);

        WordDto wordDto = new WordMapper().getWordDtoFromWord(word, 0);

        Assert.assertNotNull(wordDto.getWordId());
        Assert.assertNotNull(wordDto.getNumOfRepeating());
        Assert.assertNotNull(wordDto.getWordName());

        boolean wordIdIsOk = wordDto.getWordId() == 0L;
        boolean wordNameIsOk = wordDto.getWordName().equals("word");
        boolean wordNumOfRepeatingIsOk = wordDto.getNumOfRepeating().equals(0);

        Assert.assertTrue(wordIdIsOk);
        Assert.assertTrue(wordNameIsOk);
        Assert.assertTrue(wordNumOfRepeatingIsOk);
    }

    @Test
    public void wordToWordDtoLessArgs() {
        Word word = new Word(0L, "word", null, null);

        WordDto wordDto = new WordMapper().getWordDtoFromWord(word);

        Assert.assertNotNull(wordDto.getWordId());
        Assert.assertNotNull(wordDto.getWordName());

        boolean wordIdIsOk = wordDto.getWordId() == 0L;
        boolean wordNameIsOk = wordDto.getWordName().equals("word");

        Assert.assertTrue(wordIdIsOk);
        Assert.assertTrue(wordNameIsOk);
    }
}