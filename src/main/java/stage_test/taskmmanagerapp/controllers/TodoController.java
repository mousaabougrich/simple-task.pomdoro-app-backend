package stage_test.taskmmanagerapp.controllers;

import stage_test.taskmmanagerapp.entities.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage_test.taskmmanagerapp.services.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping("/add")
    public Todo addTodo(@RequestBody Todo todo) throws Exception {
        return todoService.addTodo(todo);
    }

    @DeleteMapping("/delete")
    public void deleteTodo(@RequestParam int id) throws Exception {
        todoService.deleteTodo(id);
    }

    @PutMapping("/update")
    public Todo updateTodo(@RequestBody Todo todo, @RequestParam int id) throws Exception {
        return todoService.updateTodo(todo, id);
    }

    @GetMapping("/getbyid")
    public List<Todo> findTodoByPersonId(@RequestParam int id) throws Exception {
        return todoService.findTodoByPersonId(id);
    }

    @GetMapping("/getbystatus")
    public List<Todo> findTodoByStatus(@RequestParam String status) throws Exception {
        return todoService.findTodoByStatus(status);
    }
}
