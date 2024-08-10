package stage_test.taskmmanagerapp.reposotries;

import stage_test.taskmmanagerapp.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRep extends JpaRepository<Person, Integer> {
    Person findByUsername(String username);
    Boolean existsByUsername(String username);
}
