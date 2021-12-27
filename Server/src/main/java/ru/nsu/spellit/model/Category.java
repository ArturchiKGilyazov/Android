package ru.nsu.spellit.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Builder
@Table(name = "CATEGORY_TABLE")
public class Category {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "category_id")
    private Long categoryId;

    @Getter
    @Setter
    @Column(name = "category_name")
    private String name;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "WORD_CATEGORY",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "word_id")}
    )
    private List<Word> words;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Getter
    @Setter
    private Boolean isDefault = false;

    public Category(Long categoryId, String name, List<Word> words, User user, Boolean isDefault) {
        setCategoryId(categoryId);
        setName(name);
        setWords(words);
        setUser(user);
        setIsDefault(isDefault);
    }

    public Category() {
    }

    public Category(User user) {
        this.user = user;
    }

    public static Category getDefaultCategory(User user) {
        Category category = new Category(user);
        category.setName("default");
        category.setIsDefault(true);
        return category;
    }

    public void setCategory(Category category) {
        this.setCategoryId(category.getCategoryId());
        this.setName(category.getName());
        this.setWords(category.getWords());
        this.setIsDefault(category.getIsDefault());
    }

    public void addWord(Word word) {
        if (words == null) {
            words = new LinkedList<>();
        }
        words.add(word);
    }

    public void deleteWord(Word word) {
        if (word != null)
            words.remove(word);
    }
}
