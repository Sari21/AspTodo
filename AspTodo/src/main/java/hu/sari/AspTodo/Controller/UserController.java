package hu.sari.AspTodo.Controller;

import hu.sari.AspTodo.Model.ResponseUser;
import hu.sari.AspTodo.Model.User;
import hu.sari.AspTodo.security.Service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @Secured("ROLE_ADMIN")
    @PostMapping
    public User addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }
    @Secured("ROLE_ADMIN")
    @GetMapping
    public Iterable<User> getAllUsers(){
        return this.userService.getUsers();
    }
    @Secured("ROLE_ADMIN")
    @GetMapping(path = "{id}")
    public Optional<User> getUserById(@PathVariable("id") long id){
        return this.userService.getUserById(id);
    }
    @Secured("ROLE_ADMIN")
    @PatchMapping
    public User updateUser(@RequestBody User user){
        return this.userService.updateUser(user);
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping (path = "{id}")
    @Transactional
    public void deleteUserById(@PathVariable("id") long id){
        this.userService.deleteUserById(id);
    }
    @Secured("ROLE_USER")
    @GetMapping("names")
    public List<ResponseUser> getNames(){
        return this.userService.getNames();
    }
}
