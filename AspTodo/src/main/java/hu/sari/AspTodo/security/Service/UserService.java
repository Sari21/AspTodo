package hu.sari.AspTodo.security.Service;

import hu.sari.AspTodo.Model.ResponseUser;
import hu.sari.AspTodo.Model.Role;
import hu.sari.AspTodo.Model.User;
import hu.sari.AspTodo.Repository.RoleRepository;
import hu.sari.AspTodo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private  TaskService taskService;

    @Autowired
    public UserService(UserRepository userRepository,  RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public Iterable<User> getUsers(){
        return this.userRepository.findAll();
    }
    public Optional<User> getUserById(long id){
        return this.userRepository.findById(id);
    }
   /* public User updateUser(User newUser)
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
    }*/
    public User updateUser(User newUser)
    {
        Optional<User> oldUser = this.userRepository.findById(newUser.getId());
        if(oldUser.isPresent()){
            if(newUser.getPassword() != null){
                oldUser.get().setPassword(newUser.getPassword());
            }
            oldUser.get().setUsername(newUser.getUsername());
            oldUser.get().setName(newUser.getName());
            oldUser.get().setEmail(newUser.getEmail());

            Set<Role> newRoles = new HashSet<Role>();
            oldUser.get().clearRoles();
            for(Role r : newUser.getRoles()){
                Optional<Role> tmprole = this.roleRepository.findByName(r.getName());
                if(tmprole.isPresent()){
                    newRoles.add(tmprole.get());
                }
            }
            oldUser.get().setRoles(newRoles);
            return this.userRepository.save(oldUser.get());
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
    public Optional<User> findUserByUsername(String username){
        return this.userRepository.findByUsername(username);
    }


}
