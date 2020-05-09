package hu.sari.AspTodo.security.Service;

import hu.sari.AspTodo.Model.ResponseUser;
import hu.sari.AspTodo.Model.User;
import hu.sari.AspTodo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public Iterable<User> getUsers(){
        return this.userRepository.findAll();
    }
    public Optional<User> getUserById(long id){
        return this.userRepository.findById(id);
    }
    public User updateUser(User user){
        return this.userRepository.save(user);
    }
    public User addUser(User user){
        return this.userRepository.save(user);
    }
    public void deleteUserById(long id){
        this.userRepository.deleteById(id);
    }
    public List<ResponseUser> getNames(){
            Iterable<User> users = this.userRepository.findAll();
            List<ResponseUser> list = new ArrayList<>();
            for(User u: users){
            list.add(new ResponseUser(u));
            }
            //Elements can traverse in any order
            return list;
        }
}
