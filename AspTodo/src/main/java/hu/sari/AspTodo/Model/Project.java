package hu.sari.AspTodo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min=3, max = 100)
    private String name;
    @Size(min=3, max=500)
    private String description;
    @Column(unique=true)
    private long jobNumber;
    @OneToMany
    private List<Task> tasks ;

    public Project() {

    }

    public Project(@JsonProperty("name")String name, @JsonProperty("description")String description, @JsonProperty("jobNumber") long jobNumber) {

        this.name = name;
        this.description = description;
        this.jobNumber = jobNumber;
        this.tasks = new ArrayList<>();
    }

    public long getId() {
        return id;
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
    public void addTask(Task t){
        this.tasks.add(t);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
