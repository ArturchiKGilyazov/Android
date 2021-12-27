package ru.nsu.spellit.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.spellit.model.User;
import ru.nsu.spellit.user.security.SecurityConfig;
import ru.nsu.spellit.user.security.jwt.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Operation(
            summary = "Добавление пользователя"
    )
    @PostMapping(SecurityConfig.REGISTER_ENDPOINT)
    public ResponseEntity<Map<String, String>> addUser(
            @RequestBody @Parameter(description = "Сущность пользователя", required = true) UserDto user) {
        log.info("client wants to register user : {} ", user.getUsername());
        Map<String, String> response = new HashMap<>();
        try {
            response.put("userId", userService.addUser(user).toString());
        } catch (NoSuchElementException e) {
            log.warn("user with the same name already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(SecurityConfig.LOGIN_ENDPOINT)
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto userDto) {
        Map<String, String> response = new HashMap<>();
        try {
            log.info("user : {} wants to authenticate", userDto.getUsername());
            String username = userDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userDto.getPassword()));
            User user = userService.getUser(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username);
            response.put("username", username);
            response.put("token", token);
            response.put("userId", user.getUserId().toString());

        } catch (AuthenticationException e) {
            log.info("invalid username or password");
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.resolve(500));
//            throw new BadCredentialsException("Invalid username or password");
        }

        return ResponseEntity.ok(response);
    }
}
