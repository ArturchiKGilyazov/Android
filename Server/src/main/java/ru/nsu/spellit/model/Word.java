package ru.nsu.spellit.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Builder
@Table(name = "WORD_TABLE")
public class Word {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "word_id")
    private Long wordId;

    @Getter
    @Setter
    @Column(unique = true)
    private String wordName;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "words")
    private List<Category> categories;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id")
    private List<LearningInfo> learningInfo;

    public Word() {
    }

    public Word(Long wordId, String name, List<Category> categories, List<LearningInfo> learningInfo) {
        this.setWordId(wordId);
        this.setWordName(name);
        this.setCategories(categories);
        this.setLearningInfo(learningInfo);
    }

    public void setWord(Word word) {
        this.setCategories(word.getCategories());
        this.setWordName(word.getWordName());
        this.setLearningInfo(word.getLearningInfo());
    }

    public void addCategory(Category category) {
        if (categories == null) {
            categories = new LinkedList<>();
        }
        categories.add(category);
    }

    public void addLearningInfo(LearningInfo info) {
        if (learningInfo == null) {
            learningInfo = new LinkedList<>();
        }
        learningInfo.add(info);
    }

    public void deleteCategory(Category category) {
        categories.remove(category);
    }
}
