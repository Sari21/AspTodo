package hu.sari.AspTodo.Repository;

import hu.sari.AspTodo.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    public Iterable<Project> findAllByOrderByIdDesc();
}
