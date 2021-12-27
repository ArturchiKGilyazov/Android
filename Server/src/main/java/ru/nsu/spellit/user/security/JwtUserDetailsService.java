package ru.nsu.spellit.user.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nsu.spellit.model.User;
import ru.nsu.spellit.user.UserService;
import ru.nsu.spellit.user.security.jwt.JwtUser;
import ru.nsu.spellit.user.security.jwt.JwtUserFactory;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUser(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User with user name : " + userName + " - not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN \"loadUserByUsername\" jwtUser for user with name \"{}\" was successfully created!", userName);
        return jwtUser;
    }

}
