package ru.nsu.spellit.word;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.spellit.model.Category;
import ru.nsu.spellit.model.Word;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findAllByCategoriesContains(Category category);

    Word findByWordName(String wordName);
}
