package hu.sari.AspTodo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private long jobNumber;

    public Project() {

    }

    public Project(@JsonProperty("name")String name, @JsonProperty("description")String description, @JsonProperty("jobNumber") long jobNumber) {

        this.name = name;
        this.description = description;
        this.jobNumber = jobNumber;
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
}
