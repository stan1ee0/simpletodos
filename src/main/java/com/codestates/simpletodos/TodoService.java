package com.codestates.simpletodos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> findAllTodos() {
        return todoRepository.findAll();
    }

    public Todo findTodo(long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Todo updateTodo(Todo todo, long id) {
        Todo foundTodo = findTodo(id);

        if (todo.getTitle() != null)
            foundTodo.setTitle(todo.getTitle());
        if (todo.getOrder() > 0)
            foundTodo.setOrder(todo.getOrder());
        foundTodo.setCompleted(todo.isCompleted());

        return todoRepository.save(foundTodo);
    }

    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }

    public void deleteTodo(long id) {
        findTodo(id);

        todoRepository.deleteById(id);
    }
}
