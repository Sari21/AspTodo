package hu.sari.AspTodo.security.Service;

import hu.sari.AspTodo.Model.*;
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
    public ResponseTask addTask(ResponseTask t, long projectId){
        Optional<Project> p = this.projectRepository.findById(projectId);
        Optional<User> u = this.userRepository.findByUsername(t.getUserName());
        if(p.isPresent() && u.isPresent()){
            Task newTask = new Task(t.getTitle(), t.getDescription(), u.get(), p.get());
            p.get().addTask(newTask);
            this.taskRepository.save(newTask);
            this.projectRepository.save(p.get());
            return new ResponseTask(newTask);
        }
        else return null;
    }

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

    public ResponseTask updateIsDone(long projectId, long taskId){
        Optional<Project> p = projectRepository.findById(projectId);
        Task task = null;
        if(p.isPresent()){
            for(Task t : p.get().getTasks()){
                if (t.getId() == taskId){
                    task = t;
                    t.setDone(true);
                    taskRepository.save(t);
                }
            }
            projectRepository.save(p.get());
        if(task != null)
            return (new ResponseTask(task));
        }
        return null;
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
    public void deleteTasksByUser(User user){
        this.taskRepository.deleteAllByUser(user);
    }
}
