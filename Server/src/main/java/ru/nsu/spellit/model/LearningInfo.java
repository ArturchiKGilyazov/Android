package ru.nsu.spellit.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "LEARNING_TABLE")
public class LearningInfo {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "info_id")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private Integer numOfRepeating;

    @Getter
    @Setter
    private Boolean isLearned = false;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;

    public LearningInfo() {
    }

    public LearningInfo(Long id, Long userId, Integer numOfRepeating, Boolean isLearned, Word word) {
        setId(id);
        setUserId(userId);
        setNumOfRepeating(numOfRepeating);
        setIsLearned(isLearned);
        setWord(word);
    }

    public void setInfo(LearningInfo info) {
        setId(info.getId());
        setUserId(info.getUserId());
        setIsLearned(info.getIsLearned());
        setNumOfRepeating(info.getNumOfRepeating());
        setWord(info.getWord());
    }
}
