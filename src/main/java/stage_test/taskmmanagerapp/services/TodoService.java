package stage_test.taskmmanagerapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import stage_test.taskmmanagerapp.entities.Status;
import stage_test.taskmmanagerapp.entities.Todo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage_test.taskmmanagerapp.reposotries.TodoRep;
import stage_test.taskmmanagerapp.reposotries.PersonRep;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class TodoService {
    @Autowired
    private TodoRep todoRep;
    @Autowired
    private PersonRep personRep;

    public Todo addTodo(Todo todo) throws Exception {
        Optional<Todo> todoEx = todoRep.findById(todo.getId());
        if (todoEx.isPresent()) {
            throw new Exception("Todo is already added");
        }

        return todoRep.save(todo);
    }

    public void deleteTodo(int todoId) throws Exception {
        todoRep.deleteById(todoId);
    }

    public Todo updateTodo(Todo todo, int id) throws Exception {
        Optional<Todo> todoOptional = todoRep.findById(id);
        if (todoOptional.isEmpty()) {
            throw new Exception("Todo not found");
        }

        Todo oldTodo = todoOptional.get();
        oldTodo.setTitle(todo.getTitle());
        oldTodo.setDescription(todo.getDescription());
        oldTodo.setStatus(todo.getStatus());
        oldTodo.setCreatedDate(todo.getCreatedDate());
        oldTodo.setPerson(todo.getPerson());

        return todoRep.save(oldTodo);
    }

    public List<Todo> findTodoByPersonId(int personId) throws Exception {
        return todoRep.findByPersonId(personId);
    }

    public List<Todo> findTodoByStatus(String status) throws Exception {
        Status enumStatus;
        try {
            enumStatus = Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Invalid status value: " + status);
        }
        return todoRep.findByStatus(enumStatus);
    }
}
