package hu.sari.AspTodo.Repository;

import hu.sari.AspTodo.Model.Role;
import hu.sari.AspTodo.Model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}