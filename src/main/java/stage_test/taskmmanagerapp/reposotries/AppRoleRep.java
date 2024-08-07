package stage_test.taskmmanagerapp.reposotries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage_test.taskmmanagerapp.entities.AppRole;

@Repository
public interface AppRoleRep extends JpaRepository<AppRole, Integer> {
    AppRole findByRolename(String name);
}
