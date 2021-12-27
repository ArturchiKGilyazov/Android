package ru.nsu.spellit.user.security.jwt;

import ru.nsu.spellit.model.User;

public final class JwtUserFactory {

    public JwtUserFactory() {

    }

    public static JwtUser create(User user) {
        return new JwtUser(user.getUserId(), user.getUsername(), user.getPassword(), user.getCategories());
    }

}
