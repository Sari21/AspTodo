package hu.sari.AspTodo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(max = 100)
    private String title;
    @NotNull
    @Size(max = 500)
    private String description;
    private boolean isDone;
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private User user;
    @JsonIgnore
    @ManyToOne
    private Project project;
    public Task() {
    }
    public Task( String title, String description, User user ) {
        this.description = description;
        this.title = title;
        this.user = user;
        this.isDone = false;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
