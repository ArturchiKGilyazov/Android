package ru.nsu.spellit.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.spellit.model.Category;
import ru.nsu.spellit.model.User;
import ru.nsu.spellit.model.Word;
import ru.nsu.spellit.user.UserRepository;
import ru.nsu.spellit.word.WordService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final WordService wordService;
    private final UserRepository userRepository;
    private final CategoryMapper categoryMapper;

    public Long addCategory(CategoryDto categoryDto, Long userId) {
        Category category = categoryMapper.getCategory(categoryDto);
        if (categoryRepository.findByUserUserIdAndName(userId, category.getName()) != null) {
            return -1L;
        }
        category.setUser(userRepository.findById(userId).orElse(null));
        return categoryRepository.save(category).getCategoryId();
    }

    public Long addCategory(Category category, Long userId) {
        category.setUser(userRepository.findById(userId).orElse(null));
        return categoryRepository.save(category).getCategoryId();
    }

    public CategoryDto getCategoryDto(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) throw new NoSuchElementException("category");
        return categoryMapper.getCategoryDto(category);

    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<CategoryDto> getCategoriesDto(User user) {
        return categoryRepository.findAllByUser(user).stream().map(categoryMapper::getCategoryDto).collect(Collectors.toList());
    }


    public List<Category> getCategories(User user) {
        return categoryRepository.findAllByUser(user);
    }


    public void deleteCategory(Long id) {
        Category category = getCategory(id);
        List<Word> words = category.getWords();
        for (Word word : words) {
            wordService.deleteWordFromCategory(word.getWordId(), category.getCategoryId());
        }
        categoryRepository.deleteById(id);
    }


    public void updateCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.getCategory(categoryDto);
        Category updatedCategory = getCategory(category.getCategoryId());
        updatedCategory.setCategory(category);
        categoryRepository.save(updatedCategory);
    }

    public CategoryDto getDefaultCategory(User user) {
        Category category = categoryRepository.findByUserAndIsDefault(user, true);
        if (category == null) throw new NoSuchElementException();
        return categoryMapper.getCategoryDto(category);
    }
}
