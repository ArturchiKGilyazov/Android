package ru.nsu.spellit.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Builder
@Table(name = "USER_TABLE")
public class User {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "user_id")
    private Long userId;

    @Getter
    @Setter
    @Column(unique = true)
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "categoryId")
    private List<Category> categories = new ArrayList<>();

    @Getter
    @Setter
    private Integer numOfRepeatingToKnow;

    @Getter
    @Setter
    private Integer numOfLearnedWords;

    public User() {
    }

    public User(Long userId, String username, String password, List<Category> categories, Integer numOfRepeatingToKnow, Integer numOfLearnedWords) {
        this.setUserId(userId);
        this.setUsername(username);
        this.setPassword(password);
        this.setCategories(categories);
        this.setNumOfRepeatingToKnow(numOfRepeatingToKnow);
        this.setNumOfLearnedWords(numOfLearnedWords);
    }

    public void setUser(User user) {
        this.setUserId(user.getUserId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setNumOfRepeatingToKnow(user.getNumOfRepeatingToKnow());
        this.setNumOfLearnedWords(user.getNumOfLearnedWords());
    }

    public void addCategory(Category category) {
        if(categories==null)
            categories=new LinkedList<>();
        categories.add(category);
    }
}
