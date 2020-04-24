package hu.sari.AspTodo.Service;

import hu.sari.AspTodo.Model.Project;
import hu.sari.AspTodo.Model.Task;
import hu.sari.AspTodo.Model.User;
import hu.sari.AspTodo.Repository.ProjectRepository;
import hu.sari.AspTodo.Repository.TaskRepository;
import hu.sari.AspTodo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Task addTask(Task t, long projectId, long userId){
        Project p = this.projectRepository.findById(projectId).get();
        User u = this.userRepository.findById(userId).get();
        if(p != null && u != null){
            t.setProject(p);
            t.setUser(u);
            return this.taskRepository.save(t);
        }
        else return null;
    }
    public Iterable<Task> findAllTasksByUserId(long userId){
        Optional<User> u = this.userRepository.findById(userId);
        if(u.isPresent()){
            return this.taskRepository.findAllByUser(u.get());
        }
        return null;
    }
    public Iterable<Task> findAllTasksByProjectId(long projectId){
        Optional<Project> p = this.projectRepository.findById(projectId);
        if(p.isPresent()){
            return this.taskRepository.findAllByProject(p.get());
        }
        return null;
    }
    public Iterable<Task> findTasksByProjectIdAndUserId(long projectId, long userId){
        Optional<User> u = this.userRepository.findById(userId);
        Optional<Project> p = this.projectRepository.findById(projectId);
        if(u.isPresent() && p.isPresent()){
        return this.taskRepository.findAllByUserAndProject(u.get(), p.get());
        }
        return null;
    }
    public Task updateIsDone(long taskId, boolean isDone){
        Optional<Task> t = taskRepository.findById(taskId);
        if(t.isPresent()){
            t.get().setDone(isDone);
            return taskRepository.save(t.get());
        }
        else return null;
    }
    public void deleteTaskById(long id){
        this.taskRepository.deleteById(id);
    }
}
