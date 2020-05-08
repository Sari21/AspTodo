package hu.sari.AspTodo.Controller;

import hu.sari.AspTodo.Model.ResponseTask;
import hu.sari.AspTodo.Model.Task;
import hu.sari.AspTodo.security.Service.TaskService;
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
   /* @GetMapping("user/{userId}")
    public List<ResponseTask> getTasksByUserId(@PathVariable("userId") long userId){
        return this.taskService.findAllTasksByUserId(userId);
    }

    */
    @GetMapping("username/{userName}")
    public List<ResponseTask> getTasksByUserName(@PathVariable("userName") String userName){
        return this.taskService.findAllTasksByUserName(userName);
    }
  /*  @GetMapping("project/{projectId}")
    public List<ResponseTask> getTasksByProjectId(@PathVariable("projectId") long projectId){
        return this.taskService.findAllTasksByProjectId(projectId);
    }

   */
    /*@GetMapping("user/{userId}/project/{projectId}")
    public List<ResponseTask> getTasksByProjectId(@PathVariable("userId") long userId, @PathVariable("projectId") long projectId){
        return this.taskService.findTasksByProjectIdAndUserId(projectId, userId);
    }

     */
    /*
    @GetMapping("user/{username}/project/{projectId}")
    public List<ResponseTask> getTasksByProjectIdAndUsername(@PathVariable("username") String username, @PathVariable("projectId") long projectId){
        return this.taskService.findTasksByProjectIdAndUsername(projectId, username);
    }

     */
    @PostMapping("user/{username}/project/{projectId}")
    public ResponseTask addNewTask(@RequestBody Task t, @PathVariable("username") String username, @PathVariable("projectId") long projectId){
        return this.taskService.addTask(t, projectId, username);
    }
    @DeleteMapping(path="{taskId}/project/{projectId}")
    public void deleteTaskById(@PathVariable("taskId") long taskId, @PathVariable("projectId") long projectId){
        this.taskService.deleteTaskById(taskId, projectId);
    }
    @PatchMapping(path = "{id}")
    public ResponseTask updateTaskIsDone(@PathVariable("id") long id, @RequestBody boolean isDone){
        return this.taskService.updateIsDone(id, isDone);
    }

}
