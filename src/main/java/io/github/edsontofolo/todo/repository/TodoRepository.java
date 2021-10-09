package io.github.edsontofolo.todo.repository;

import io.github.edsontofolo.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
