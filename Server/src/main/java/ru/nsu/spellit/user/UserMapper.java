package ru.nsu.spellit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.spellit.category.CategoryMapper;
import ru.nsu.spellit.model.User;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final CategoryMapper mapper;

    public UserDto getUserDto(User user) {
        UserDto.UserDtoBuilder userDto = UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .numOfRepeatingToKnow(user.getNumOfRepeatingToKnow())
                .numOfLearnedWords(user.getNumOfLearnedWords());
        if (user.getCategories() != null)
            userDto.categories(user.getCategories().stream().map(mapper::getCategoryDto).collect(Collectors.toList()));
        return userDto.build();
    }

    public User getUser(UserDto userDto) {
        User.UserBuilder user = User.builder()
                .userId(userDto.getUserId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .numOfRepeatingToKnow(userDto.getNumOfRepeatingToKnow())
                .numOfLearnedWords(userDto.getNumOfLearnedWords());
        if (userDto.getCategories() != null)
            user.categories(userDto.getCategories().stream().map(mapper::getCategory).collect(Collectors.toList()));
        return user.build();
    }

}
