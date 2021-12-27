package ru.nsu.spellit.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.spellit.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String userName);
}
