package hu.sari.AspTodo.Controller;

import hu.sari.AspTodo.Model.ResponseTask;
import hu.sari.AspTodo.Model.Task;
import hu.sari.AspTodo.security.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/task")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("username/{userName}")
    public List<ResponseTask> getTasksByUserName(@PathVariable("userName") String userName){
        return this.taskService.findAllTasksByUserName(userName);
    }

    @PostMapping("project/{projectId}")
    public ResponseEntity<ResponseTask> addNewTask(@RequestBody ResponseTask t, @PathVariable("projectId") long projectId){
        ResponseTask rT = this.taskService.addTask(t, projectId);
        if(rT == null){
            return ResponseEntity.badRequest().build();
        }
        else{
            return new ResponseEntity<ResponseTask>(rT, HttpStatus.CREATED);
        }
    }
    @DeleteMapping(path="{taskId}/project/{projectId}")
    public void deleteTaskById(@PathVariable("taskId") long taskId, @PathVariable("projectId") long projectId){
        this.taskService.deleteTaskById(taskId, projectId);
    }
    @PatchMapping(path = "project/{projectId}/task/{taskId}")
    public ResponseTask updateTaskIsDone(@PathVariable("projectId") long projectId, @PathVariable("taskId") long taskId ){
        return this.taskService.updateIsDone(projectId, taskId);
    }


}
