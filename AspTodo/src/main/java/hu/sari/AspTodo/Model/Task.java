package hu.sari.AspTodo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private boolean isDone;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Task() {
    }
    public Task(@JsonProperty("description")String description, @JsonProperty("title")String title) {
        this.description = description;
        this.title = title;

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
}
