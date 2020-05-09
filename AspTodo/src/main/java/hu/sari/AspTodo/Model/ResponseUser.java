package hu.sari.AspTodo.Model;

public class ResponseUser {
    private final long id;
    private final String name;
    private final String username;

    public ResponseUser(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getUsername() {
        return username;
    }
}
