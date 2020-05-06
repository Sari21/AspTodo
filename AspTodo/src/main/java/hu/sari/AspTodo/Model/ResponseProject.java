package hu.sari.AspTodo.Model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ResponseProject {

    private long id;
    private String name;
    private String description;
    private long jobNumber;
    private List<ResponseTask> tasks ;

    public ResponseProject(Project p) {
        id = p.getId();
        name = p.getName();
        description = p.getDescription();
        jobNumber = p.getJobNumber();
        tasks = new ArrayList<>();
        for(Task t : p.getTasks()){
            tasks.add(new ResponseTask(t));
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(long jobNumber) {
        this.jobNumber = jobNumber;
    }

    public List<ResponseTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<ResponseTask> tasks) {
        this.tasks = tasks;
    }
}
