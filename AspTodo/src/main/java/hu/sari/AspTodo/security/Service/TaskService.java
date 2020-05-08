package hu.sari.AspTodo.security.Service;

import hu.sari.AspTodo.Model.Project;
import hu.sari.AspTodo.Model.ResponseTask;
import hu.sari.AspTodo.Model.Task;
import hu.sari.AspTodo.Model.User;
import hu.sari.AspTodo.Repository.ProjectRepository;
import hu.sari.AspTodo.Repository.TaskRepository;
import hu.sari.AspTodo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository){
        this.taskRepository  = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }
    public ResponseTask addTask(Task t, long projectId, String username){
        Optional<Project> p = this.projectRepository.findById(projectId);
        Optional<User> u = this.userRepository.findByUsername(username);
        if(p.isPresent() && u.isPresent()){
            t.setUser(u.get());
            p.get().addTask(t);
            this.taskRepository.save(t);
            this.projectRepository.save(p.get());
            return new ResponseTask(t);
        }
        else return null;
    }
    /*
    public List<ResponseTask> findAllTasksByUserId(long userId){
        Optional<User> u = this.userRepository.findById(userId);
        if(u.isPresent()){

            Iterable<Task> it =  this.taskRepository.findAllByUser(u.get());
            List<ResponseTask> list = new ArrayList<>();
            ResponseTask rT;
            for(Task t : it){
                rT = new ResponseTask(t);
                list.add(rT);
            }
            return list;
        }
        return null;
    }

     */
    public List<ResponseTask> findAllTasksByUserName(String userName){
        Optional<User> u = this.userRepository.findByUsername(userName);
        if(u.isPresent()){

            Iterable<Task> it =  this.taskRepository.findAllByUserOrderByIdDesc(u.get());
            List<ResponseTask> list = new ArrayList<>();
            ResponseTask rT;
            for(Task t : it){
                rT = new ResponseTask(t);
                list.add(rT);
            }
            return list;
        }
        return null;
    }
  /*  public List<ResponseTask> findAllTasksByProjectId(long projectId){
        Optional<Project> p = this.projectRepository.findById(projectId);
        if(p.isPresent()){
            Iterable<Task> it = this.taskRepository.findAllByProject(p.get());
            List<ResponseTask> list = new ArrayList<>();
            ResponseTask rT;
            for(Task t : it) {
                rT = new ResponseTask(t);
                list.add(rT);
            }
            return list;
        }
        return null;
    }

   */
   /* public List<ResponseTask> findTasksByProjectIdAndUserId(long projectId, long userId){
        Optional<User> u = this.userRepository.findById(userId);
        Optional<Project> p = this.projectRepository.findById(projectId);
        if(u.isPresent() && p.isPresent()){
            Iterable<Task> it = this.taskRepository.findAllByUserAndProject(u.get(), p.get());
            List<ResponseTask> list = new ArrayList<>();
            ResponseTask rT;
            for(Task t : it) {
                rT = new ResponseTask(t);
                list.add(rT);
            }
            return list;
        }
        return null;
    }

    */
   /*
    public List<ResponseTask> findTasksByProjectIdAndUsername(long projectId, String username){
        Optional<User> u = this.userRepository.findByUsername(username);
        Optional<Project> p = this.projectRepository.findById(projectId);
        if(u.isPresent() && p.isPresent()){
            Iterable<Task> it = this.taskRepository.findAllByUserAndProject(u.get(), p.get());
            List<ResponseTask> list = new ArrayList<>();
            ResponseTask rT;
            for(Task t : it) {
                rT = new ResponseTask(t);
                list.add(rT);
            }
            return list;
        }
        return null;
    }

    */
    public ResponseTask updateIsDone(long taskId, boolean isDone){
        Optional<Task> t = taskRepository.findById(taskId);
        if(t.isPresent()){
            t.get().setDone(isDone);
            taskRepository.save(t.get());
            return new ResponseTask(t.get());
        }
        else return null;
    }
    public void deleteTaskById(long taskId, long projectId){

       Optional<Task> t =this.taskRepository.findById(taskId);
       Optional<Project> p = this.projectRepository.findById(projectId);
       if(t.isPresent() && p.isPresent()){
       p.get().getTasks().remove(t.get());
       this.projectRepository.save(p.get());
       this.taskRepository.deleteById(taskId);
       }
    }
}
