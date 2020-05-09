package hu.sari.AspTodo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Table(name = "project", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "jobNumber"
        }),
})

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(max = 100)
    private String name;
    @Size(max=1000)
    @NotNull
    private String description;
    @NotNull
    private long jobNumber;
    @OneToMany(cascade = CascadeType.ALL,  orphanRemoval=true)
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
