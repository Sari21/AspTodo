package hu.sari.AspTodo.Controller;

import hu.sari.AspTodo.Model.User;
import hu.sari.AspTodo.security.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    public User addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }
    @GetMapping
    public Iterable<User> getAllUsers(){
        return this.userService.getUsers();
    }
    @GetMapping(path = "{id}")
    public Optional<User> getUserById(@PathVariable("id") long id){
        return this.userService.getUserById(id);
    }
    @PutMapping
    public User updateUser(@RequestBody User user){
        return this.userService.updateUser(user);
    }
    @DeleteMapping ("{id}")
    public void deleteUserById(@PathVariable("id") long id){
        this.userService.deleteUserById(id);
    }
}
