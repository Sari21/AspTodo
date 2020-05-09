package hu.sari.AspTodo.security.Service;

import hu.sari.AspTodo.Model.Project;
import hu.sari.AspTodo.Model.ResponseProject;
import hu.sari.AspTodo.Model.Task;
import hu.sari.AspTodo.Repository.ProjectRepository;
import hu.sari.AspTodo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    @Autowired
    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository){
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }
    public Project addProject(Project project){
        return this.projectRepository.save(project);
    }
    public Project updateProject(Project project){
        return this.projectRepository.save(project);
    }
    public Iterable<Project> getAllProjects(){
        return this.projectRepository.findAllByOrderByIdDesc();
    }
    public ResponseProject getProjectById(long id){
        Optional<Project> p = this.projectRepository.findById(id);
        if(p.isPresent()){
            return new ResponseProject(p.get());
        }
        return null;
    }
    public void deleteProjectById(long id){
        this.projectRepository.deleteById(id);
    }

    public ResponseProject getProjectByIdAndUser(long id, String username){
        Optional<Project> p = this.projectRepository.findById(id);
        if(p.isPresent()){

        List<Task> filteredTasks = new ArrayList<>();
        for(Task t : p.get().getTasks()){
            if(t.getUser().getUsername().equals(username)){
                filteredTasks.add(0, t);
            }
        }
        p.get().setTasks(filteredTasks);

        return new ResponseProject(p.get());
        }
        else return null;
    }
}
