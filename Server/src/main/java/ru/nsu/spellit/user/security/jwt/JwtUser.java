package ru.nsu.spellit.user.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.nsu.spellit.model.Category;

import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String userName;
    private final String password;
    private final List<Category> categories;

    public JwtUser(Long id, String userName, String password, List<Category> categories) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.categories = categories;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return userName;
    }

    @JsonIgnore
    public List<Category> getCategories() {
        return categories;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
