package hu.sari.AspTodo.Controller;

import hu.sari.AspTodo.Model.Project;
import hu.sari.AspTodo.security.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/project")
public class ProjectController {
    private final ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping
    public Iterable<Project> getAllProjects(){
        return this.projectService.getAllProjects();
    }
    @GetMapping(path = "{id}")
    public Optional<Project> getProjectById(@PathVariable("id") long id){
        return this.projectService.getProjectById(id);
    }
    @PostMapping
    public Project addProject(@RequestBody Project project){
        return this.projectService.addProject(project);
    }
    @PutMapping
    public Project updateProject(@RequestBody Project project){
        return this.projectService.updateProject(project);
    }
    @DeleteMapping(path = "{id}")
    public void deleteProjectById(@PathVariable("id") long  id){
        this.projectService.deleteProjectById(id);
    }
}
