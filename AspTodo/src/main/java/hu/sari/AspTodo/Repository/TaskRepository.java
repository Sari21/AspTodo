package hu.sari.AspTodo.Repository;

import hu.sari.AspTodo.Model.Project;
import hu.sari.AspTodo.Model.Task;
import hu.sari.AspTodo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findAllByUserOrderByIdDesc(User u);
}
