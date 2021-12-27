package ru.nsu.spellit.learningInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.spellit.model.LearningInfo;
import ru.nsu.spellit.model.Word;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LearningInfoService {
    private final LearningInfoRepository repository;

    public Long addLearningInfo(LearningInfo learningInfo) {
        return repository.save(learningInfo).getId();
    }

    public LearningInfo getLearningInfo(Long id) {
        return repository.findById(id).orElse(null);
    }

    public LearningInfo getLearningInfo(Long userId, Word word) {
        return repository.findByUserIdAndWord(userId, word);
    }

    public List<LearningInfo> getLearningInfos() {
        return repository.findAll();
    }

    public List<LearningInfo> getLearningInfos(Long userId) {
        return repository.findAllByUserId(userId);
    }


    public void deleteLearningInfo(Long id) {
        repository.deleteById(id);
    }


    public void updateLearningInfo(LearningInfo learningInfo) {
        LearningInfo updatedLearningInfo = getLearningInfo(learningInfo.getId());
        updatedLearningInfo.setInfo(learningInfo);
        repository.save(updatedLearningInfo);
    }
}
