package hu.sari.AspTodo.Model;

import javax.persistence.CascadeType;

public class ResponseTask {
    private long id;
    private String title;
    private String description;
    private boolean isDone;
    private long userId;
    private String userName;
    public ResponseTask(Task t){
        this.id = t.getId();
        this.title = t.getTitle();
        this.description = t.getDescription();
        this.isDone = t.isDone();
        this.userId = t.getUser().getId();
        this.userName = t.getUser().getName();
    }
    public ResponseTask(){
        this.isDone = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
