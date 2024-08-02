package stage_test.taskmmanagerapp.reposotries;

import stage_test.taskmmanagerapp.entities.Status;
import stage_test.taskmmanagerapp.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRep extends JpaRepository<Todo, Integer> {
    List<Todo> findByPersonId(int personId);
    List<Todo> findByStatus(Status status);

}
