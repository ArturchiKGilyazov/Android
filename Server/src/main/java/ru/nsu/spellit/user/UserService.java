package ru.nsu.spellit.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.spellit.category.CategoryDto;
import ru.nsu.spellit.category.CategoryService;
import ru.nsu.spellit.model.Category;
import ru.nsu.spellit.model.User;
import ru.nsu.spellit.word.WordDto;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final UserMapper userMapper;

    public Long addUser(UserDto userDto) {
        User user = userMapper.getUser(userDto);
        Long userId = userRepository.save(user).getUserId();
        user.setUserId(userId);
        Category defaultForUser = Category.getDefaultCategory(user);
        defaultForUser.setCategoryId(categoryService.addCategory(defaultForUser, userId));
        user.addCategory(defaultForUser);
        updateUser(user);
        return userId;
    }

    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) throw new NoSuchElementException("user");
        return user;
    }

    public User getUser(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) throw new NoSuchElementException("user");
        return user;
    }

    public UserDto getUserDto(Long userId) {
        User user = getUser(userId);
        List<CategoryDto> categories = categoryService.getCategoriesDto(user);
        UserDto userDto = userMapper.getUserDto(user);
        userDto.setCategories(categories);
        for (CategoryDto categoryDto : userDto.getCategories()) {
            for (WordDto wordDto : categoryDto.getWords()) {
                if (wordDto.getNumOfRepeating().equals(user.getNumOfRepeatingToKnow()))
                    wordDto.setLearned(true);
            }
        }
        return userDto;
    }

    public UserDto getUserDto(String userName) {
        User user = getUser(userName);
        List<CategoryDto> categories = categoryService.getCategoriesDto(user);
        UserDto userDto = userMapper.getUserDto(user);
        userDto.setCategories(categories);
        for (CategoryDto categoryDto : userDto.getCategories()) {
            for (WordDto wordDto : categoryDto.getWords()) {
                if (wordDto.getNumOfRepeating().equals(user.getNumOfRepeatingToKnow()))
                    wordDto.setLearned(true);
            }
        }
        return userDto;
    }

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new LinkedList<>();
        for (User user : users) {
            UserDto userDto = userMapper.getUserDto(user);
            List<CategoryDto> categories = categoryService.getCategoriesDto(user);
            userDto.setCategories(categories);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public void deleteUser(Long userId) {
        List<Category> categories = categoryService.getCategories(getUser(userId));
        for (Category category : categories)
            categoryService.deleteCategory(category.getCategoryId());
        userRepository.deleteById(userId);
    }

    public void updateUser(UserDto userDto) {
        User updatedUser = getUser(userDto.getUserId());
        updatedUser.setUser(userMapper.getUser(userDto));
        userRepository.save(updatedUser);
    }

    void updateUser(User user) {
        User updatedUser = getUser(user.getUserId());
        updatedUser.setUser(user);
        userRepository.save(updatedUser);
    }
}
