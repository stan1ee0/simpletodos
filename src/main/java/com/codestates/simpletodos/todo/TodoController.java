package com.codestates.simpletodos.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class TodoController {
    private final String baseUrl = "http://localhost:8080/";

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TodoDto postTodo(@Valid @RequestBody TodoDto todoDto) {
        Todo createdTodo = todoService.createTodo(new Todo(todoDto));

        return new TodoDto(createdTodo, baseUrl + String.valueOf(createdTodo.getId()));
    }

    @GetMapping
    public List<TodoDto> getAllTodos() {
        List<Todo> allTodos = todoService.findAllTodos();

        return allTodos.stream()
                .map(todo -> new TodoDto(todo, baseUrl + String.valueOf(todo.getId())))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public TodoDto getTodo(@PathVariable long id) {
        Todo foundTodo = todoService.findTodo(id);

        return new TodoDto(foundTodo, baseUrl + String.valueOf(foundTodo.getId()));
    }

    @PatchMapping(value = "/{id}")
    public TodoDto patchTodo(@PathVariable long id,
                          @RequestBody TodoDto todoDto) {
        Todo updatedTodo = todoService.updateTodo(new Todo(todoDto), id);

        return new TodoDto(updatedTodo, baseUrl + String.valueOf(updatedTodo.getId()));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteAllTodos() {
        todoService.deleteAllTodos();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteTodo(@PathVariable long id) {
        todoService.deleteTodo(id);
    }
}
