package hu.sari.AspTodo.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    private final String userName;
    private String password;
    @Value("${some.key:false}")
    private boolean active;
    private List<GrantedAuthority> authorities;



    public MyUserDetails(User user) {
        this.userName = user.getName();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Arrays.stream(user.getRole().toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        for (GrantedAuthority g : authorities
        ) {

            System.out.println(g);
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}