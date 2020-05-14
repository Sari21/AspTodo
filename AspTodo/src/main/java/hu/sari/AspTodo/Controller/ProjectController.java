package hu.sari.AspTodo.Controller;

import hu.sari.AspTodo.Model.Project;
import hu.sari.AspTodo.Model.ResponseProject;
import hu.sari.AspTodo.Model.ResponseTask;
import hu.sari.AspTodo.security.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Iterable<Project>> getAllProjects(){

        return ResponseEntity.ok(this.projectService.getAllProjects());
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<ResponseProject> getProjectById(@PathVariable("id") long id){
        Optional<Project> project = this.projectService.getProjectById(id);
        if(project.isPresent()){
        return ResponseEntity.ok(new ResponseProject(project.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("user/{username}/project/{projectId}")
    public ResponseEntity<ResponseProject> getTasksByProjectId(@PathVariable("username") String username, @PathVariable("projectId") long projectId){
        Optional<Project> project =  this.projectService.getProjectByIdAndUser(projectId, username);
        if(project.isPresent()){
            return ResponseEntity.ok(new ResponseProject(project.get()));
        }
        return ResponseEntity.notFound().build();
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
