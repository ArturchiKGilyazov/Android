package ru.nsu.spellit.learningInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.spellit.model.LearningInfo;
import ru.nsu.spellit.model.Word;

import java.util.List;

@Repository
public interface LearningInfoRepository extends JpaRepository<LearningInfo, Long> {
    LearningInfo findByUserIdAndWord(Long userId, Word word);

    List<LearningInfo> findAllByUserId(Long userId);
}
