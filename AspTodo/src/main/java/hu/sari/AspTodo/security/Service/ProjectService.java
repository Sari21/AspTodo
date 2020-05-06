package hu.sari.AspTodo.security.Service;

import hu.sari.AspTodo.Model.Project;
import hu.sari.AspTodo.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
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
        return this.projectRepository.findById(id);
    }
    public void deleteProjectById(long id){
        this.projectRepository.deleteById(id);
    }
}
