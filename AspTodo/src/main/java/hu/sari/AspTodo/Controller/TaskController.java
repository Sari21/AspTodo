package hu.sari.AspTodo.Controller;

import hu.sari.AspTodo.Model.ResponseTask;
import hu.sari.AspTodo.Model.Task;
import hu.sari.AspTodo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/task")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    @GetMapping("user/{userId}")
    public List<ResponseTask> getTasksByUserId(@PathVariable("userId") long userId){
        return this.taskService.findAllTasksByUserId(userId);
    }
    @GetMapping("project/{projectId}")
    public List<ResponseTask> getTasksByProjectId(@PathVariable("projectId") long projectId){
        return this.taskService.findAllTasksByProjectId(projectId);
    }
    @GetMapping("user/{userId}/project/{projectId}")
    public List<ResponseTask> getTasksByProjectId(@PathVariable("userId") long userId, @PathVariable("projectId") long projectId){
        return this.taskService.findTasksByProjectIdAndUserId(projectId, userId);
    }
    @PostMapping("user/{userId}/project/{projectId}")
    public ResponseTask addNewTask(@RequestBody Task t, @PathVariable("userId") long userId, @PathVariable("projectId") long projectId){
        return this.taskService.addTask(t, projectId, userId);
    }
    @DeleteMapping(path="{id}")
    public void deleteTaskById(@PathVariable("id") long id){
        this.taskService.deleteTaskById(id);
    }
    @PatchMapping(path = "{id}")
    public ResponseTask updateTaskIsDone(@PathVariable("id") long id, @RequestBody boolean isDone){
        return this.taskService.updateIsDone(id, isDone);
    }

}
