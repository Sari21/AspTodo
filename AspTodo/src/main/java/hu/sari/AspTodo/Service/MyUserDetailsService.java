package hu.sari.AspTodo.Service;
import hu.sari.AspTodo.Model.MyUserDetails;

import hu.sari.AspTodo.Model.User;
import hu.sari.AspTodo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(userName);
        if (user.isPresent()) {
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return new ArrayList<>();
                }

                @Override
                public String getPassword() {
                    return user.get().getPassword();
                }

                @Override
                public String getUsername() {
                    return user.get().getName();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return false;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return false;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return false;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
        }
        else{
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }
    }

        //user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        //return user.map(MyUserDetails::new).get();

/*
        @Override
        public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
            if ("techgeeknext".equals(username)) {
                return new User("techgeeknext", "$2a$10$ixlPY3AAd4ty1l6E2IsQ9OFZi2ba9ZQE0bP7RFcGIWNhyFrrT3YUi",
                        new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        }
 */




}
