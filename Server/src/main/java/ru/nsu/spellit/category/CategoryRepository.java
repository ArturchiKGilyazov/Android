package ru.nsu.spellit.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.spellit.model.Category;
import ru.nsu.spellit.model.User;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUser(User user);

    Category findByUserAndIsDefault(User user, Boolean isDefault);

    Category findByUserUserIdAndName(Long userId, String categoryName);
}
