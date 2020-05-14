package hu.sari.AspTodo.security.Service;

import hu.sari.AspTodo.Model.Project;
import hu.sari.AspTodo.Model.Task;
import hu.sari.AspTodo.Model.User;
import hu.sari.AspTodo.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
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
    public Optional<Project> getProjectById(long id){
        Optional<Project> p = this.projectRepository.findById(id);
        return p;
    }
    public Optional<Project>findById(long id){
        return this.projectRepository.findById(id);
    }
    public void deleteProjectById(long id){
        this.projectRepository.deleteById(id);
    }

    public Optional<Project> getProjectByIdAndUser(long id, String username){
        Optional<Project> p = this.projectRepository.findById(id);
        if(p.isPresent()){

        List<Task> filteredTasks = new ArrayList<>();
        for(Task t : p.get().getTasks()){
            if(t.getUser().getUsername().equals(username)){
                filteredTasks.add(0, t);
            }
        }
        p.get().setTasks(filteredTasks);

        }
        return p;

    }
    public void deleteTasksFromProjectByUser(User u){
        ArrayList<Task> delete = new ArrayList<>();
        for(Project p : this.projectRepository.findAll()){

           /* Iterator<Task> i = p.getTasks().iterator();
            while (i.hasNext()) {
                Task s = i.next(); // must be called before you can call i.remove()
                // Do something
                i.remove();
            }

            */
            for(Task t : p.getTasks()){
                if(t.getUser() == u){
                    delete.add(t);

                }
            }
                this.taskService.deleteAll(delete);
                p.getTasks().removeAll(delete);
                delete.clear();
                this.updateProject(p);
        }
    }

}
