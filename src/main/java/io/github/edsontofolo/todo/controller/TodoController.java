package io.github.edsontofolo.todo.controller;

import io.github.edsontofolo.todo.model.Todo;
import io.github.edsontofolo.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("http://localhost:4200")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @PostMapping
    public Todo save(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @GetMapping("{id}")
    public Todo getById(@PathVariable Long id) {
        return this.todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Todo> getAll() {
        return this.todoRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.todoRepository.deleteById(id);
    }

    @PatchMapping("{id}/done")
    public Todo markAsDone(@PathVariable Long id) {
        return this.todoRepository.findById(id)
                .map(todo -> {
                    todo.setDone(Boolean.TRUE);
                    todo.setDoneDate(LocalDateTime.now());
                    return this.todoRepository.save(todo);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
