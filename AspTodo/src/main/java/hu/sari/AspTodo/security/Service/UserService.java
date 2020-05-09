package hu.sari.AspTodo.security.Service;

import hu.sari.AspTodo.Model.Project;
import hu.sari.AspTodo.Model.ResponseUser;
import hu.sari.AspTodo.Model.Task;
import hu.sari.AspTodo.Model.User;
import hu.sari.AspTodo.Repository.ProjectRepository;
import hu.sari.AspTodo.Repository.TaskRepository;
import hu.sari.AspTodo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private  TaskService taskService;
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
    public User updateUser(User newUser)
    {
        Optional<User> oldUser = this.userRepository.findById(newUser.getId());
        if(oldUser.isPresent()){

        if(newUser.getPassword() == null){
            newUser.setPassword(oldUser.get().getPassword());
            newUser.setRoles(oldUser.get().getRoles());
        }
        return this.userRepository.save(newUser);
        }
        return null;
    }
    public User addUser(User user){
        return this.userRepository.save(user);
    }
    public void deleteUserById(long id){

        Optional<User> u = this.userRepository.findById(id);
        if(u.isPresent()){
            this.taskService.deleteTasksByUser(u.get());
            this.userRepository.deleteById(id);
        }
    }
    public List<ResponseUser> getNames(){
            Iterable<User> users = this.userRepository.findAll();
            List<ResponseUser> list = new ArrayList<>();
            for(User u: users){
            list.add(new ResponseUser(u));
            }
            return list;
        }
}
